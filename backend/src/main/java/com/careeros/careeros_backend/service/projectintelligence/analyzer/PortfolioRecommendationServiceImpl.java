package com.careeros.careeros_backend.service.projectintelligence.analyzer;

import com.careeros.careeros_backend.dto.projectportfolio.PortfolioRecommendation;
import com.careeros.careeros_backend.model.Project;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PortfolioRecommendationServiceImpl
        implements PortfolioRecommendationService {

            @Override
public PortfolioRecommendation analyze(
        List<Project> projects
) {

    if (projects == null || projects.isEmpty()) {

        return PortfolioRecommendation.builder()
                .strongestProject("No projects")
                .strongestArea("N/A")
                .weakestArea("N/A")
                .recommendedNextProject("Build your first project")
                .overallRecommendation("Start building engineering projects.")
                .strongestTechnologies(List.of())
                .missingTechnologies(List.of())
                .build();

    }

    Map<String, Integer> techFrequency = new HashMap<>();

    Project strongestProject = null;
    int highestScore = -1;

    int architecture = 0;
    int testing = 0;
    int security = 0;
    int deployment = 0;
    int documentation = 0;

    int analyzed = 0;

    for (Project project : projects) {

        if (project.getTechStack() != null) {

            for (String tech : project.getTechStack()) {

                techFrequency.merge(
                        tech,
                        1,
                        Integer::sum
                );

            }

        }

        if (project.getEngineeringAnalysis() == null) {
            continue;
        }

        analyzed++;

        var engineering = project.getEngineeringAnalysis();

        if (engineering.getEngineeringScore() != null &&
                engineering.getEngineeringScore() > highestScore) {

            highestScore = engineering.getEngineeringScore();
            strongestProject = project;

        }

        architecture += safe(engineering.getArchitectureScore());
        testing += safe(engineering.getTestingQuality());
        security += safe(engineering.getSecurityScore());
        deployment += safe(engineering.getDeploymentReadiness());
        documentation += safe(engineering.getDocumentationQuality());

    }

    if (analyzed == 0) {
        analyzed = 1;
    }

    architecture /= analyzed;
    testing /= analyzed;
    security /= analyzed;
    deployment /= analyzed;
    documentation /= analyzed;

    Map<String, Integer> engineeringAreas = new HashMap<>();

    engineeringAreas.put("Architecture", architecture);
    engineeringAreas.put("Testing", testing);
    engineeringAreas.put("Security", security);
    engineeringAreas.put("Deployment", deployment);
    engineeringAreas.put("Documentation", documentation);

    String strongestArea =
            engineeringAreas.entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse("Engineering");

    String weakestArea =
            engineeringAreas.entrySet()
                    .stream()
                    .min(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse("Engineering");

    List<String> strongestTechnologies =
            techFrequency.entrySet()
                    .stream()
                    .sorted((a, b) ->
                            Integer.compare(
                                    b.getValue(),
                                    a.getValue()
                            ))
                    .limit(5)
                    .map(Map.Entry::getKey)
                    .toList();

    List<String> missingTechnologies =
            recommendMissingTech(
                    strongestTechnologies
            );

    return PortfolioRecommendation.builder()

            .strongestProject(
                    strongestProject != null
                            ? strongestProject.getProjectName()
                            : "N/A"
            )

            .strongestArea(
                    strongestArea
            )

            .weakestArea(
                    weakestArea
            )

            .recommendedNextProject(
                    recommendProject(
                            weakestArea
                    )
            )

            .overallRecommendation(
                    buildRecommendation(
                            weakestArea
                    )
            )

            .strongestTechnologies(
                    strongestTechnologies
            )

            .missingTechnologies(
                    missingTechnologies
            )

            .build();

}

private int safe(Integer value) {
    return value == null ? 0 : value;
}

private String recommendProject(
        String weakestArea
) {

    return switch (weakestArea) {

        case "Testing" ->
                "Build a project with comprehensive unit and integration testing";

        case "Security" ->
                "Build a secure authentication and authorization system";

        case "Deployment" ->
                "Deploy a cloud-native application using Docker and CI/CD";

        case "Architecture" ->
                "Build a scalable microservices application";

        case "Documentation" ->
                "Create a production-grade open-source project";

        default ->
                "Build a production-ready full-stack application";
    };

}

private String buildRecommendation(
        String weakestArea
) {

    return "Improve your " + weakestArea.toLowerCase()
            + " to strengthen your engineering portfolio.";

}

private List<String> recommendMissingTech(
        List<String> strongest
) {

    List<String> recommendations = new ArrayList<>();

    if (!strongest.contains("Docker"))
        recommendations.add("Docker");

    if (!strongest.contains("AWS"))
        recommendations.add("AWS");

    if (!strongest.contains("Redis"))
        recommendations.add("Redis");

    if (!strongest.contains("Kafka"))
        recommendations.add("Kafka");

    if (!strongest.contains("Kubernetes"))
        recommendations.add("Kubernetes");

    return recommendations;

}
        }