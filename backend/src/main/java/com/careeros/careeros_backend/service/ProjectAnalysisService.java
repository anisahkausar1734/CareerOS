package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.ProjectAnalysisResponse;
import com.careeros.careeros_backend.dto.ProjectInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectAnalysisService {

    public ProjectAnalysisResponse analyzeProject(
            ProjectInfo project
    ) {

        int complexityScore =
                getComplexityScore(
                        project.getComplexityLevel()
                );

        int technicalScore =
                getTechnicalScore(project);

        int architectureScore =
                getArchitectureScore(
                        project.getArchitectureType()
                );

        int deploymentScore =
                getDeploymentScore(project);

        int impactScore =
                getImpactScore(
                        project.getEstimatedUsers(),
                        project.getRevenueGenerated()
                );

        int innovationScore =
                getInnovationScore(project);

        int collaborationScore =
                getCollaborationScore(
                        project.getTeamSize()
                );

        int finalScore =
                complexityScore
                + technicalScore
                + architectureScore
                + deploymentScore
                + impactScore
                + innovationScore
                + collaborationScore;

        finalScore = Math.min(finalScore, 100);

        return ProjectAnalysisResponse.builder()
                .projectName(project.getProjectName())
                .complexityScore(complexityScore)
                .technicalScore(technicalScore)
                .deploymentScore(deploymentScore)
                .architectureScore(architectureScore)
                .impactScore(impactScore)
                .innovationScore(innovationScore)
                .collaborationScore(collaborationScore)
                .finalScore(finalScore)
                .feedback(getFeedback(finalScore))
                .build();
    }

    private int getComplexityScore(
            String complexityLevel
    ) {

        if (complexityLevel == null) {
            return 0;
        }

        switch (complexityLevel.toUpperCase()) {

            case "BEGINNER":
                return 10;

            case "INTERMEDIATE":
                return 20;

            case "ADVANCED":
                return 30;

            default:
                return 0;
        }
    }

    private int getTechnicalScore(
            ProjectInfo project
    ) {

        int score = 0;

        if (project.isHasFrontend()) {
            score += 5;
        }

        if (project.isHasBackend()) {
            score += 5;
        }

        if (project.isHasDatabase()) {
            score += 5;
        }

        if (project.isHasAuthentication()) {
            score += 5;
        }

        return score;
    }

    private int getArchitectureScore(
            String architectureType
    ) {

        if (architectureType == null) {
            return 0;
        }

        switch (architectureType.toUpperCase()) {

            case "MONOLITH":
                return 5;

            case "LAYERED":
                return 10;

            case "MICROSERVICES":
                return 15;

            case "EVENT_DRIVEN":
                return 15;

            default:
                return 0;
        }
    }

    private int getDeploymentScore(
            ProjectInfo project
    ) {

        int score = 0;

        if (project.isDeployed()) {
            score += 5;
        }

        if (project.isHasCICD()) {
            score += 5;
        }

        return score;
    }

    private int getImpactScore(
            Integer estimatedUsers,
            Double revenueGenerated
    ) {

        int score = 0;

        if (estimatedUsers != null) {

            if (estimatedUsers >= 1000) {
                score += 10;
            }
            else if (estimatedUsers >= 100) {
                score += 7;
            }
            else if (estimatedUsers >= 10) {
                score += 5;
            }
        }

        if (revenueGenerated != null
                && revenueGenerated > 0) {

            score += 5;
        }

        return Math.min(score, 10);
    }

    private int getInnovationScore(
            ProjectInfo project
    ) {

        int score = 0;

        if (project.isUsesAI()) {
            score += 5;
        }

        if (project.isResearchBased()) {
            score += 3;
        }

        if (project.isOpenSource()) {
            score += 2;
        }

        return Math.min(score, 10);
    }

    private int getCollaborationScore(
            Integer teamSize
    ) {

        if (teamSize == null) {
            return 0;
        }

        if (teamSize >= 5) {
            return 5;
        }

        if (teamSize >= 2) {
            return 3;
        }

        return 1;
    }

    private String getFeedback(
            int score
    ) {

        if (score >= 90) {
            return "Excellent project with strong industry value";
        }

        if (score >= 75) {
            return "Strong project with good resume impact";
        }

        if (score >= 60) {
            return "Good project but can be improved further";
        }

        if (score >= 40) {
            return "Average project. Add deployment, architecture and impact";
        }

        return "Beginner-level project. Consider building a more complex real-world solution";
    }
}