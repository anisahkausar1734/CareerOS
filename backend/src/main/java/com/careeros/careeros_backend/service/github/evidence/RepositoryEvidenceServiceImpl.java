package com.careeros.careeros_backend.service.github.evidence;

import com.careeros.careeros_backend.dto.github.RepositoryEvidenceResponse;
import com.careeros.careeros_backend.dto.github.RepositoryRawEvidence;
import com.careeros.careeros_backend.service.github.GithubGraphQLService;
import com.careeros.careeros_backend.service.github.GithubRestService;
import com.careeros.careeros_backend.service.github.builder.RepositoryEvidenceBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RepositoryEvidenceServiceImpl
        implements RepositoryEvidenceService {

    private final GithubGraphQLService graphQLService;

    private final GithubRestService restService;

    private final ImportantFileCollector importantFileCollector;

    private final RepositoryEvidenceBuilder evidenceBuilder;

    @Override
    @SuppressWarnings("unchecked")
    public RepositoryEvidenceResponse collect(
            String githubUrl,
            boolean forceRefresh
    ) {

        /*
         * Parse GitHub URL
         */
        String cleaned = githubUrl
                .replace("https://github.com/", "")
                .replace("http://github.com/", "")
                .replaceAll("/$", "");

        String[] parts = cleaned.split("/");

        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid GitHub URL");
        }

        String owner = parts[0];
        String repository = parts[1];

        /*
         * GraphQL Overview
         */
        Map<String, Object> graphQL =
                graphQLService.getRepositoryOverview(
                        owner,
                        repository
                );

        /*
         * Default Branch
         */
        Map<String, Object> data =
                (Map<String, Object>) graphQL.get("data");

        Map<String, Object> repo =
                (Map<String, Object>) data.get("repository");

        Map<String, Object> defaultBranchRef =
                (Map<String, Object>) repo.get("defaultBranchRef");

        String defaultBranch = "main";

        if (defaultBranchRef != null) {
            defaultBranch =
                    (String) defaultBranchRef.get("name");
        }

        /*
         * Repository Tree
         */
        Map<String, Object> treeResponse =
                restService.getRepositoryTree(
                        owner,
                        repository,
                        defaultBranch
                );

        List<Map<String, Object>> repositoryTree =
                (List<Map<String, Object>>) treeResponse.get("tree");

        /*
         * Root Contents
         */
        List<Map<String, Object>> rootContents =
                restService.getRootContents(
                        owner,
                        repository
                );

        /*
         * Important Files
         */
        Map<String, String> importantFiles =
                importantFileCollector.collect(
                        owner,
                        repository,
                        repositoryTree
                );

        /*
         * README
         */
        String readme = importantFiles.entrySet()
                .stream()
                .filter(entry ->
                        entry.getKey()
                                .toLowerCase()
                                .contains("readme"))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse("");
/*
 * Build Raw Evidence
 */
RepositoryRawEvidence rawEvidence =
        RepositoryRawEvidence.builder()

                .graphQLResponse(graphQL)

                .repositoryTree(repositoryTree)

                .rootContents(rootContents)

                .importantFiles(importantFiles)

                .readme(readme)

                .build();

/*
 * Build Engineering Evidence
 */
return evidenceBuilder.build(
        rawEvidence
);

    }

}