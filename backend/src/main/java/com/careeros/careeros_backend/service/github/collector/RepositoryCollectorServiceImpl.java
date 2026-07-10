package com.careeros.careeros_backend.service.github.collector;

import com.careeros.careeros_backend.dto.github.RepositoryRawEvidence;
import com.careeros.careeros_backend.service.github.GithubGraphQLService;
import com.careeros.careeros_backend.service.github.GithubRestService;
import com.careeros.careeros_backend.service.github.evidence.ImportantFileCollector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RepositoryCollectorServiceImpl
        implements RepositoryCollectorService {

    private final GithubGraphQLService
            graphQLService;

    private final GithubRestService
            restService;

    private final ImportantFileCollector
            importantFileCollector;

    @Override
    @SuppressWarnings("unchecked")
    public RepositoryRawEvidence collect(
            String githubUrl
    ) {

        String cleaned =
                githubUrl
                        .replace("https://github.com/", "")
                        .replace("http://github.com/", "")
                        .replaceAll("/$", "");

        String[] split =
                cleaned.split("/");

        if (split.length < 2) {
            throw new IllegalArgumentException(
                    "Invalid GitHub repository URL."
            );
        }

        String owner =
                split[0];

        String repository =
                split[1];

        /*
         * GraphQL
         */

        Map<String, Object> graphQL =
                graphQLService
                        .getRepositoryOverview(
                                owner,
                                repository
                        );

        /*
         * Repository
         */

        Map<String, Object> data =
                (Map<String, Object>)
                        graphQL.get("data");

        Map<String, Object> repo =
                (Map<String, Object>)
                        data.get("repository");

        Map<String, Object> defaultBranchRef =
                (Map<String, Object>)
                        repo.get("defaultBranchRef");

        String branch = "main";

        if (defaultBranchRef != null) {

            branch =
                    (String)
                            defaultBranchRef.get("name");

        }

        /*
         * Tree
         */

        Map<String, Object> treeResponse =
                restService.getRepositoryTree(
                        owner,
                        repository,
                        branch
                );

        List<Map<String, Object>> tree =
                (List<Map<String, Object>>)
                        treeResponse.get("tree");

        /*
         * Root
         */

        List<Map<String, Object>> root =
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
                        tree
                );

        /*
         * README
         */

        String readme =
                importantFiles.entrySet()

                        .stream()

                        .filter(e ->
                                e.getKey()
                                        .toLowerCase()
                                        .contains("readme"))

                        .map(Map.Entry::getValue)

                        .findFirst()

                        .orElse("");

        return RepositoryRawEvidence

                .builder()

                .graphQLResponse(graphQL)

                .repositoryTree(tree)

                .rootContents(root)

                .importantFiles(importantFiles)

                .readme(readme)

                .build();

    }

}