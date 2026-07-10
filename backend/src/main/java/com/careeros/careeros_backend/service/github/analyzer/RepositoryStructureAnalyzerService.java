package com.careeros.careeros_backend.service.github.analyzer;

import java.util.List;
import java.util.Map;

public interface RepositoryStructureAnalyzerService {

    boolean hasReadme(
            List<Map<String, Object>> rootContents
    );

    boolean hasDocker(
            List<Map<String, Object>> rootContents
    );

    boolean hasGithubActions(
            List<Map<String, Object>> rootContents
    );

    boolean hasDocumentation(
            List<Map<String, Object>> rootContents
    );

    boolean hasTests(
            List<Map<String, Object>> tree
    );

    int countControllers(
            List<Map<String, Object>> tree
    );

    int countServices(
            List<Map<String, Object>> tree
    );

    int countRepositories(
            List<Map<String, Object>> tree
    );

    int countDTOs(
            List<Map<String, Object>> tree
    );

    int countModels(
            List<Map<String, Object>> tree
    );

    int countConfigs(
            List<Map<String, Object>> tree
    );

}