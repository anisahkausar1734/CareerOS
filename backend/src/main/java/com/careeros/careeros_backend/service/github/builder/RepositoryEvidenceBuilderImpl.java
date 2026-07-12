package com.careeros.careeros_backend.service.github.builder;

import com.careeros.careeros_backend.dto.github.RepositoryEvidenceResponse;
import com.careeros.careeros_backend.dto.github.RepositoryRawEvidence;
import com.careeros.careeros_backend.dto.github.evidence.*;
import com.careeros.careeros_backend.dto.github.intelligence.RepositoryIntelligence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor

public class RepositoryEvidenceBuilderImpl
        implements RepositoryEvidenceBuilder {

    @Override
@SuppressWarnings("unchecked")
public RepositoryEvidenceResponse build(

    RepositoryRawEvidence raw,

    RepositoryIntelligence intelligence

)
{

       Map<String, Object> graphQLResponse =
        raw.getGraphQLResponse();

List<Map<String, Object>> repositoryTree =
        raw.getRepositoryTree();

List<Map<String, Object>> rootContents =
        raw.getRootContents();

Map<String, String> importantFiles =
        raw.getImportantFiles();

String readme =
        raw.getReadme();


       Map<String, Object> data =
        (Map<String, Object>) graphQLResponse.get("data");

Map<String, Object> repository =
        (Map<String, Object>) data.get("repository");

RepositoryIdentity identity =
        buildIdentity(repository);

RepositoryHealth health =
        buildHealth(repository);

RepositoryStructure structure =
        buildStructure(
                repositoryTree,
                importantFiles
        );

RepositoryDocumentation documentation =
        buildDocumentation(
                readme,
                importantFiles
        );

RepositoryActivity activity =
        buildActivity(repository);

RepositoryDeployment deployment =
        buildDeployment(
                importantFiles
        );

EngineeringEvidence evidence =
        buildEngineeringEvidence(
                repository,
                repositoryTree,
                importantFiles,
                readme
        );
return RepositoryEvidenceResponse.builder()

        .identity(identity)

        .health(health)

        .structure(structure)

        .documentation(documentation)

        .activity(activity)

        .deployment(deployment)

        .engineeringEvidence(evidence)

        .intelligence(intelligence)

        .facts(
                intelligence.getFacts()
        )

        .signals(
                intelligence.getSignals()
        )

        .capabilities(
                intelligence.getCapabilities()
        )

        .build();

    }

    /*
     * ====================================================
     * Identity
     * ====================================================
     */

    private RepositoryIdentity buildIdentity(
            Map<String, Object> repository
    ) {

        return RepositoryIdentity

                .builder()

                .repositoryName(
                        (String) repository.get("name")
                )

                .owner(
                        extractOwner(repository)
                )

                .repositoryUrl(
                        (String) repository.get("url")
                )

                .homepage(
                        (String) repository.get("homepageUrl")
                )

                .description(
                        (String) repository.get("description")
                )
                
                .build();

    }

    /*
     * ====================================================
     * Health
     * ====================================================
     */

    private RepositoryHealth buildHealth(
            Map<String, Object> repository
    ) {

        return RepositoryHealth

                .builder()

                .stars(
                        (Integer) repository.get("stargazerCount")
                )

                .forks(
                        (Integer) repository.get("forkCount")
                )

                .repositorySize(
                        (Integer) repository.get("diskUsage")
                )

                .build();

    }

    /*
     * ====================================================
     * Structure
     * ====================================================
     */

    private RepositoryStructure buildStructure(

            List<Map<String, Object>> repositoryTree,

            Map<String, String> importantFiles

    ) {

        return RepositoryStructure

                .builder()

                .totalFiles(
                        repositoryTree.size()
                )

                .importantFiles(
                        new ArrayList<>(
                                importantFiles.keySet()
                        )
                )

                .build();

    }

    /*
     * ====================================================
     * Documentation
     * ====================================================
     */

    private RepositoryDocumentation buildDocumentation(

            String readme,

            Map<String, String> importantFiles

    ) {

        return RepositoryDocumentation

                .builder()

                .hasReadme(
                        readme != null
                )

                .readme(
                        readme
                )

                .hasLicense(
                        importantFiles.containsKey("LICENSE")
                )

                .build();

    }

    /*
     * ====================================================
     * Activity
     * ====================================================
     */

    private RepositoryActivity buildActivity(
            Map<String, Object> repository
    ) {

        return RepositoryActivity

                .builder()

                .build();

    }

    /*
     * ====================================================
     * Deployment
     * ====================================================
     */

    private RepositoryDeployment buildDeployment(

            Map<String, String> importantFiles

    ) {

        return RepositoryDeployment

                .builder()

                .hasDocker(

                        importantFiles.containsKey(
                                "Dockerfile"
                        )

                )

                .hasGithubActions(

                        importantFiles.keySet()

                                .stream()

                                .anyMatch(

                                        f -> f.startsWith(
                                                ".github/workflows"
                                        )

                                )

                )

                .build();

    }

    /*
     * ====================================================
     * Engineering Evidence
     * ====================================================
     */

    @SuppressWarnings("unchecked")
    private EngineeringEvidence buildEngineeringEvidence(

            Map<String, Object> repository,

            List<Map<String, Object>> repositoryTree,

            Map<String, String> importantFiles,

            String readme

    ) {

        Map<String, Integer> languages =
                new HashMap<>();

        Map<String, Object> languageMap =
                (Map<String, Object>) repository.get("languages");

        if (languageMap != null) {

            List<Map<String, Object>> edges =
                    (List<Map<String, Object>>) languageMap.get("edges");

            if (edges != null) {

                for (Map<String, Object> edge : edges) {

                    Map<String, Object> node =
                            (Map<String, Object>) edge.get("node");

                    languages.put(

                            (String) node.get("name"),

                            (Integer) edge.get("size")

                    );

                }

            }

        }

        List<String> tree =
                repositoryTree

                        .stream()

                        .map(

                                e -> (String) e.get("path")

                        )

                        .toList();

        return EngineeringEvidence

                .builder()

                .languages(languages)

                .repositoryTree(tree)

                .readme(readme)
                
                

                .build();

    }

    /*
     * ====================================================
     * Helper
     * ====================================================
     */

    @SuppressWarnings("unchecked")
    private String extractOwner(
            Map<String, Object> repository
    ) {

        Map<String, Object> owner =
                (Map<String, Object>) repository.get("owner");

        if (owner == null) {
            return "";
        }

        return (String) owner.get("login");

    }

}