package com.careeros.careeros_backend.service.projectintelligence.analyzer;

import com.careeros.careeros_backend.dto.projectportfolio.PortfolioEngineeringMetrics;
import com.careeros.careeros_backend.model.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineeringPortfolioAnalyzerImpl
        implements EngineeringPortfolioAnalyzer {

    @Override
    public PortfolioEngineeringMetrics analyze(
            List<Project> projects
    ) {

        if (projects == null || projects.isEmpty()) {
            return emptyMetrics();
        }

        int analyzed = 0;

        int engineeringScore = 0;
        int architectureScore = 0;
        int codeQuality = 0;
        int maintainability = 0;
        int repositoryOrganization = 0;
        int testingQuality = 0;
        int securityScore = 0;
        int documentationQuality = 0;
        int buildQuality = 0;
        int dependencyManagement = 0;
        int deploymentReadiness = 0;
        int productionReadiness = 0;
        int innovationScore = 0;
        int technicalComplexity = 0;
        int scalability = 0;
        int evidenceCoverage = 0;
        int maturityScore = 0;

        for (Project project : projects) {

            if (project.getEngineeringAnalysis() == null) {
                continue;
            }

            analyzed++;

            var e = project.getEngineeringAnalysis();

            engineeringScore += safe(e.getEngineeringScore());
            architectureScore += safe(e.getArchitectureScore());
            codeQuality += safe(e.getCodeQuality());
            maintainability += safe(e.getMaintainability());
            repositoryOrganization += safe(e.getRepositoryOrganization());
            testingQuality += safe(e.getTestingQuality());
            securityScore += safe(e.getSecurityScore());
            documentationQuality += safe(e.getDocumentationQuality());
            buildQuality += safe(e.getBuildQuality());
            dependencyManagement += safe(e.getDependencyManagement());
            deploymentReadiness += safe(e.getDeploymentReadiness());
            productionReadiness += safe(e.getProductionReadiness());
            innovationScore += safe(e.getInnovationScore());
            technicalComplexity += safe(e.getTechnicalComplexity());
            scalability += safe(e.getScalability());
            evidenceCoverage += safe(e.getEvidenceCoverageScore());
            maturityScore += safe(e.getMaturityScore());

        }

        if (analyzed == 0) {
            return emptyMetrics();
        }

        return PortfolioEngineeringMetrics.builder()

                .portfolioScore(
                        engineeringScore / analyzed
                )

                .averageEngineeringScore(
                        engineeringScore / analyzed
                )

                .averageArchitectureScore(
                        architectureScore / analyzed
                )

                .averageCodeQuality(
                        codeQuality / analyzed
                )

                .averageMaintainability(
                        maintainability / analyzed
                )

                .averageRepositoryOrganization(
                        repositoryOrganization / analyzed
                )

                .averageTestingQuality(
                        testingQuality / analyzed
                )

                .averageSecurityScore(
                        securityScore / analyzed
                )

                .averageDocumentationQuality(
                        documentationQuality / analyzed
                )

                .averageBuildQuality(
                        buildQuality / analyzed
                )

                .averageDependencyManagement(
                        dependencyManagement / analyzed
                )

                .averageDeploymentReadiness(
                        deploymentReadiness / analyzed
                )

                .averageProductionReadiness(
                        productionReadiness / analyzed
                )

                .averageInnovationScore(
                        innovationScore / analyzed
                )

                .averageTechnicalComplexity(
                        technicalComplexity / analyzed
                )

                .averageScalability(
                        scalability / analyzed
                )

                .averageEvidenceCoverage(
                        evidenceCoverage / analyzed
                )

                .averageMaturityScore(
                        maturityScore / analyzed
                )

                .build();

    }

    private int safe(
            Integer value
    ) {

        return value == null
                ? 0
                : value;

    }

    private PortfolioEngineeringMetrics emptyMetrics() {

        return PortfolioEngineeringMetrics.builder()

                .portfolioScore(0)
                .averageEngineeringScore(0)
                .averageArchitectureScore(0)
                .averageCodeQuality(0)
                .averageMaintainability(0)
                .averageRepositoryOrganization(0)
                .averageTestingQuality(0)
                .averageSecurityScore(0)
                .averageDocumentationQuality(0)
                .averageBuildQuality(0)
                .averageDependencyManagement(0)
                .averageDeploymentReadiness(0)
                .averageProductionReadiness(0)
                .averageInnovationScore(0)
                .averageTechnicalComplexity(0)
                .averageScalability(0)
                .averageEvidenceCoverage(0)
                .averageMaturityScore(0)

                .build();

    }

}