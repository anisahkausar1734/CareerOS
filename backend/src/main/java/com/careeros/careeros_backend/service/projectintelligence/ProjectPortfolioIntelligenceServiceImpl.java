package com.careeros.careeros_backend.service.projectintelligence;

import com.careeros.careeros_backend.dto.ProjectIntelligenceResponse;
import com.careeros.careeros_backend.dto.projectportfolio.PortfolioCareerMetrics;
import com.careeros.careeros_backend.dto.projectportfolio.PortfolioEngineeringMetrics;
import com.careeros.careeros_backend.dto.projectportfolio.PortfolioRecommendation;
import com.careeros.careeros_backend.model.Project;
import com.careeros.careeros_backend.repository.ProjectRepository;
import com.careeros.careeros_backend.service.projectintelligence.analyzer.CareerPortfolioAnalyzer;
import com.careeros.careeros_backend.service.projectintelligence.analyzer.EngineeringPortfolioAnalyzer;
import com.careeros.careeros_backend.service.projectintelligence.analyzer.PortfolioRecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectPortfolioIntelligenceServiceImpl
        implements ProjectPortfolioIntelligenceService {

    private final ProjectRepository projectRepository;

    private final EngineeringPortfolioAnalyzer
            engineeringAnalyzer;

    private final CareerPortfolioAnalyzer
            careerAnalyzer;

    private final PortfolioRecommendationService
            recommendationService;

    @Override
    public ProjectIntelligenceResponse generate(
            String email
    ) {

        List<Project> projects =
                projectRepository.findByEmail(email);

        List<Project> analyzedProjects =
                projects.stream()
                        .filter(p ->
                                p.getEngineeringAnalysis() != null
                        )
                        .toList();

        if (analyzedProjects.isEmpty()) {

            return ProjectIntelligenceResponse.builder()

                    .portfolioScore(0)

                    .portfolioLevel("No Projects")

                    .portfolioVerdict(
                            "No analyzed projects available."
                    )

                    .totalProjects(
                            projects.size()
                    )

                    .analyzedProjects(0)

                    .build();

        }

        PortfolioEngineeringMetrics engineering =
                engineeringAnalyzer.analyze(
                        analyzedProjects
                );

        PortfolioCareerMetrics career =
                careerAnalyzer.analyze(
                        analyzedProjects
                );

        PortfolioRecommendation recommendation =
                recommendationService.analyze(
                        analyzedProjects
                );

        return ProjectIntelligenceResponse

                .builder()

                /*
                 * Portfolio
                 */

                .portfolioScore(
                        engineering.getPortfolioScore()
                )

                .portfolioLevel(
                        determinePortfolioLevel(
                                engineering.getPortfolioScore()
                        )
                )

                .portfolioVerdict(
                        generateVerdict(
                                engineering.getPortfolioScore()
                        )
                )

                /*
                 * Statistics
                 */

                .totalProjects(
                        projects.size()
                )

                .analyzedProjects(
                        analyzedProjects.size()
                )

                .productionReadyProjects(
                        countProductionProjects(
                                analyzedProjects
                        )
                )

                .deployedProjects(
                        countDeploymentProjects(
                                analyzedProjects
                        )
                )

                .openSourceProjects(
                        analyzedProjects.size()
                )

                /*
                 * Engineering
                 */

                .averageEngineeringScore(
                        engineering.getAverageEngineeringScore()
                )

                .averageTechnicalComplexity(
                        engineering.getAverageTechnicalComplexity()
                )

                .averageProductionReadiness(
                        engineering.getAverageProductionReadiness()
                )

                .averageDocumentationQuality(
                        engineering.getAverageDocumentationQuality()
                )

                .averageInnovationScore(
                        engineering.getAverageInnovationScore()
                )

                /*
                 * Career
                 */

                .internshipReadiness(
                        career.getInternshipReadiness()
                )

                .resumeStrength(
                        career.getResumeStrength()
                )

                .hiringSignal(
                        career.getHiringSignal()
                )

                .roleAlignment(
                        career.getRoleAlignment()
                )

                /*
                 * Recommendations
                 */

                .strongestTechnologies(
                        recommendation.getStrongestTechnologies()
                )

                .missingTechnologies(
                        recommendation.getMissingTechnologies()
                )

                .strongestProject(
                        recommendation.getStrongestProject()
                )

                .recommendedNextProject(
                        recommendation.getRecommendedNextProject()
                )

                .overallRecommendation(
                        recommendation.getOverallRecommendation()
                )

                .build();

    }

    private String determinePortfolioLevel(
            Integer score
    ) {

        if (score >= 90)
            return "Professional";

        if (score >= 80)
            return "Advanced";

        if (score >= 70)
            return "Intermediate";

        if (score >= 60)
            return "Beginner+";

        return "Beginner";

    }

    private String generateVerdict(
            Integer score
    ) {

        if (score >= 90)
            return "Excellent engineering portfolio.";

        if (score >= 80)
            return "Very strong portfolio.";

        if (score >= 70)
            return "Good portfolio with room to grow.";

        return "Continue building more production-ready projects.";

    }

    private Integer countProductionProjects(
            List<Project> projects
    ) {

        return (int) projects.stream()

                .filter(p ->
                        p.getEngineeringAnalysis() != null
                                && p.getEngineeringAnalysis()
                                .getProductionReadiness() >= 80
                )

                .count();

    }

    private Integer countDeploymentProjects(
            List<Project> projects
    ) {

        return (int) projects.stream()

                .filter(p ->
                        p.getEngineeringAnalysis() != null
                                && p.getEngineeringAnalysis()
                                .getDeploymentReadiness() >= 80
                )

                .count();

    }

}