package com.careeros.careeros_backend.service.github.analyzer;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RepositoryStructureAnalyzerServiceImpl
        implements RepositoryStructureAnalyzerService {

    /*
     * ----------------------------
     * Generic Helper Methods
     * ----------------------------
     */

    private boolean containsFile(
            List<Map<String, Object>> contents,
            String... filenames
    ) {

        return contents.stream().anyMatch(item -> {

            String name =
                    ((String) item.get("name")).toLowerCase();

            for (String filename : filenames) {

                if (name.equals(filename.toLowerCase())) {
                    return true;
                }
            }

            return false;
        });

    }

    private boolean containsKeyword(
            List<Map<String, Object>> tree,
            String... keywords
    ) {

        return tree.stream().anyMatch(item -> {

            String path =
                    ((String) item.get("path")).toLowerCase();

            for (String keyword : keywords) {

                if (path.contains(keyword.toLowerCase())) {
                    return true;
                }

            }

            return false;

        });

    }

    private int countOccurrences(
            List<Map<String, Object>> tree,
            String... keywords
    ) {

        return (int)

                tree.stream()

                        .filter(item -> {

                            String path =
                                    ((String) item.get("path")).toLowerCase();

                            for (String keyword : keywords) {

                                if (path.contains(keyword.toLowerCase())) {
                                    return true;
                                }

                            }

                            return false;

                        })

                        .count();

    }

    /*
     * ----------------------------
     * Structure Detection
     * ----------------------------
     */

    @Override
    public boolean hasReadme(
            List<Map<String, Object>> rootContents
    ) {

        return containsFile(
                rootContents,
                "README.md",
                "README"
        );

    }

    @Override
    public boolean hasDocker(
            List<Map<String, Object>> rootContents
    ) {

        return containsFile(
                rootContents,
                "Dockerfile",
                "docker-compose.yml",
                "docker-compose.yaml"
        );

    }

    @Override
    public boolean hasGithubActions(
            List<Map<String, Object>> rootContents
    ) {

        return containsFile(
                rootContents,
                ".github"
        );

    }

    @Override
    public boolean hasDocumentation(
            List<Map<String, Object>> rootContents
    ) {

        return hasReadme(rootContents);

    }

    @Override
    public boolean hasTests(
            List<Map<String, Object>> tree
    ) {

        return containsKeyword(
                tree,
                "test",
                "tests",
                "__tests__",
                "spec",
                "mockito",
                "junit",
                "jest",
                "cypress",
                "playwright",
                "vitest"
        );

    }

    /*
     * ----------------------------
     * Counts
     * ----------------------------
     */

    @Override
    public int countControllers(
            List<Map<String, Object>> tree
    ) {

        return countOccurrences(
                tree,
                "controller",
                "restcontroller",
                "apicontroller"
        );

    }

    @Override
    public int countServices(
            List<Map<String, Object>> tree
    ) {

        return countOccurrences(
                tree,
                "service",
                "serviceimpl",
                "usecase",
                "handler"
        );

    }

    @Override
    public int countRepositories(
            List<Map<String, Object>> tree
    ) {

        return countOccurrences(
                tree,
                "repository",
                "dao",
                "mapper"
        );

    }

    @Override
    public int countDTOs(
            List<Map<String, Object>> tree
    ) {

        return countOccurrences(
                tree,
                "dto",
                "request",
                "response",
                "vo"
        );

    }

    @Override
    public int countModels(
            List<Map<String, Object>> tree
    ) {

        return countOccurrences(
                tree,
                "model",
                "entity",
                "domain"
        );

    }

    @Override
    public int countConfigs(
            List<Map<String, Object>> tree
    ) {

        return countOccurrences(
                tree,
                "config",
                "configuration",
                ".properties",
                ".yml",
                ".yaml"
        );

    }

}