package com.careeros.careeros_backend.service.projectanalysis.context;

import com.careeros.careeros_backend.dto.github.RepositoryEvidenceResponse;
import com.careeros.careeros_backend.dto.github.evidence.*;
import com.careeros.careeros_backend.dto.projectanalysis.context.EngineeringContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EngineeringContextBuilderImpl
        implements EngineeringContextBuilder {

    @Override
    public EngineeringContext build(
            RepositoryEvidenceResponse evidence
    ) {

        RepositoryIdentity identity =
                evidence.getIdentity();

        RepositoryHealth health =
                evidence.getHealth();

        RepositoryStructure structure =
                evidence.getStructure();

        RepositoryDocumentation documentation =
                evidence.getDocumentation();

        RepositoryDeployment deployment =
                evidence.getDeployment();

        EngineeringEvidence engineering =
                evidence.getEngineeringEvidence();

        return EngineeringContext.builder()

                .repositoryName(
                        identity.getRepositoryName()
                )

                .description(
                        identity.getDescription()
                )

                .repositoryUrl(
                        identity.getRepositoryUrl()
                )

                .languages(
                        engineering.getLanguages()
                )

                .stars(
                        health.getStars()
                )

                .forks(
                        health.getForks()
                )

                .watchers(
                        health.getWatchers()
                )

                .contributors(
                        health.getContributors()
                )

                .hasReadme(
                        documentation.getHasReadme()
                )

                .hasLicense(
                        documentation.getHasLicense()
                )

                .hasChangelog(
                        documentation.getHasChangelog()
                )

                .hasContributingGuide(
                        documentation.getHasContributingGuide()
                )

                .facts(
    evidence.getFacts()
)

.signals(
    evidence.getSignals()
)

.capabilities(
    evidence.getCapabilities()
)
                .buildFiles(
                        structure.getBuildFiles()
                )

                .dependencyFiles(
                        structure.getDependencyFiles()
                )

                .hasDocker(
                        deployment.getHasDocker()
                )

                .hasGithubActions(
                        deployment.getHasGithubActions()
                )

                .hasDeploymentConfiguration(
                        deployment.getHasDeploymentWorkflow()
                )

                .hasOpenApi(
                        containsOpenApi(
                                engineering
                        )
                )

                .totalFiles(
                        structure.getTotalFiles()
                )

                .totalDirectories(
                        structure.getTotalDirectories()
                )

                .importantFiles(
                        structure.getImportantFiles()
                )

                .readmeSummary(
                        summarizeReadme(
                                documentation.getReadme()
                        )
                )

                .build();

    }

    private Boolean containsOpenApi(
            EngineeringEvidence engineering
    ) {

        if (engineering.getApiFiles() == null) {
            return false;
        }

        return !engineering.getApiFiles().isEmpty();

    }

    private String summarizeReadme(
            String readme
    ) {

        if (readme == null) {
            return "";
        }

        if (readme.length() <= 3000) {
            return readme;
        }

        return readme.substring(0, 3000);

    }

}