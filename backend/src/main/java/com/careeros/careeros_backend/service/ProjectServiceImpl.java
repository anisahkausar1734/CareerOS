package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.CreateProjectRequest;
import com.careeros.careeros_backend.dto.projectanalysis.ProjectAnalysisResponse;
import com.careeros.careeros_backend.dto.projectanalysis.ProjectCareerImpact;
import com.careeros.careeros_backend.dto.projectanalysis.ProjectEngineeringAnalysis;
import com.careeros.careeros_backend.dto.ProjectIntelligenceResponse;
import com.careeros.careeros_backend.dto.ProjectResponse;
import com.careeros.careeros_backend.model.Project;
import com.careeros.careeros_backend.repository.ProjectRepository;
import com.careeros.careeros_backend.repository.RoadmapRepository;
import com.careeros.careeros_backend.service.projectanalysis.orchestrator.ProjectAnalysisOrchestrator;
import com.careeros.careeros_backend.service.github.GithubGraphQLService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl
        implements ProjectService {

    private final ProjectRepository projectRepository;

    private final GithubGraphQLService githubGraphQLService;

        private final RoadmapRepository
        roadmapRepository;

        private final ProjectAnalysisOrchestrator projectAnalysisOrchestrator;

  
@Override
public ProjectIntelligenceResponse getProjectIntelligence(
        String email
) {

    throw new UnsupportedOperationException(
            "Project Intelligence V2 is currently being migrated."
    );

}

    @Override
    @SuppressWarnings("unchecked")
    public ProjectResponse createProject(
            CreateProjectRequest request
    ) {


        String[] repoInfo =
        extractOwnerAndRepo(
                request.getGithubUrl()
        );

Map<String, Object> response =
        githubGraphQLService.getRepositoryOverview(
                repoInfo[0],
                repoInfo[1]
        );

Map<String, Object> data =
        (Map<String, Object>) response.get("data");

Map<String, Object> repository =
        (Map<String, Object>) data.get("repository");


Optional<Project> existing =
        projectRepository.findByEmailAndGithubUrl(
                request.getEmail(),
                request.getGithubUrl()
        );

if (existing.isPresent()) {

    return mapProject(
            existing.get()
    );

}

        Project project =
        Project.builder()

                .email(
                        request.getEmail()
                )

                .projectName(
                        (String) repository.get("name")
                )

                .description(
                        (String) repository.get("description")
                )

                .techStack(
                        extractLanguages(repository)
                )

                .githubUrl(
                        request.getGithubUrl()
                )

                .liveUrl(
                        request.getLiveUrl()
                )

                .createdAt(
                        LocalDateTime.now()
                )

                .updatedAt(
                        LocalDateTime.now()
                )

                .build();
                        
                project.setAnalysisStatus("PROCESSING");
project.setAnalysisVersion(0);
                
                project = projectRepository.save(project);


                roadmapRepository
        .findByEmail(
                request.getEmail()
        )
        .ifPresent(
                roadmapRepository::delete
        );

        return mapProject(
                project
        );
    }


    @Override
public ProjectAnalysisResponse createAndAnalyze(
        CreateProjectRequest request
) {

    ProjectResponse project =
            createProject(request);

    return analyzeProject(
            project.getId()
    );

}
   
@Override
public ProjectAnalysisResponse reAnalyzeProject(
        String projectId
) {

    return analyzeProject(projectId);

}


    @Override
    public List<ProjectResponse> getProjects(
            String email
    ) {

        return projectRepository
                .findByEmail(
                        email
                )
                .stream()
                .map(
                        this::mapProject
                )
                .toList();
    }

    @Override
    public void deleteProject(
            String projectId
    ) {

        projectRepository.deleteById(
                projectId
        );
    }
@Override
public ProjectAnalysisResponse analyzeProject(
        String projectId
) {

    return projectAnalysisOrchestrator.analyze(
            projectId
    );

}

@Override
public ProjectResponse getProjectById(String projectId) {

    Project project = projectRepository.findById(projectId)
            .orElseThrow(() ->
                    new RuntimeException("Project not found"));

    return mapProject(project);
}



  private ProjectResponse mapProject(
        Project project
) {

    ProjectEngineeringAnalysis engineering =
            project.getEngineeringAnalysis();

    ProjectCareerImpact career =
            project.getCareerImpact();

    return ProjectResponse.builder()

            .id(
                    project.getId()
            )

            .projectName(
                    project.getProjectName()
            )

            .description(
                    project.getDescription()
            )

            .techStack(
                    project.getTechStack()
            )

            .githubUrl(
                    project.getGithubUrl()
            )

            .liveUrl(
                    project.getLiveUrl()
            )

            /*
             * ==========================
             * Engineering
             * ==========================
             */

          .projectScore(
    engineering != null
        ? engineering.getEngineeringScore()
        : null
)

            .technicalComplexity(
                    engineering != null
                            ? engineering.getTechnicalComplexity()
                            : null
            )

            .engineeringQuality(
                    engineering != null
                            ? engineering.getEngineeringScore()
                            : null
            )

            .productionReadiness(
                    engineering != null
                            ? engineering.getProductionReadiness()
                            : null
            )

            .codeQuality(
                    engineering != null
                            ? engineering.getCodeQuality()
                            : null
            )

            .industryValue(
                    engineering != null
                            ? engineering.getBusinessValue()
                            : null
            )

            /*
             * ==========================
             * Career
             * ==========================
             */

            .resumeImpact(
                    career != null
                            ? career.getResumeImpact()
                            : null
            )

            .internshipImpact(
                    career != null
                            ? career.getInternshipImpact()
                            : null
            )

            .jobImpact(
                    career != null
                            ? career.getJobImpact()
                            : null
            )

            .roleAlignment(
                    career != null
                            ? career.getRoleAlignment()
                            : null
            )

            /*
             * ==========================
             * Verdict
             * ==========================
             */

            .verdict(
                    engineering != null
                            ? engineering.getEngineeringVerdict()
                            : null
            )

            .strengths(
                    engineering != null
                            ? engineering.getStrengths()
                            : List.of()
            )

            .weaknesses(
                    engineering != null
                            ? engineering.getRisks()
                            : List.of()
            )

            .improvements(
                    engineering != null
                            ? engineering.getMissingEngineeringPractices()
                            : List.of()
            )

            .architectureScore(
        engineering != null
                ? engineering.getArchitectureScore()
                : null
)

.documentationQuality(
        engineering != null
                ? engineering.getDocumentationQuality()
                : null
)

.securityScore(
        engineering != null
                ? engineering.getSecurityScore()
                : null
)

.testingQuality(
        engineering != null
                ? engineering.getTestingQuality()
                : null
)

.deploymentReadiness(
        engineering != null
                ? engineering.getDeploymentReadiness()
                : null
)

.scalability(
        engineering != null
                ? engineering.getScalability()
                : null
)
.engineeringReview(
        engineering != null
                ? engineering.getEngineeringReview()
                : null
)

.architectureReview(
        engineering != null
                ? engineering.getArchitectureReview()
                : null
)

.documentationReview(
        engineering != null
                ? engineering.getDocumentationReview()
                : null
)

.securityReview(
        engineering != null
                ? engineering.getSecurityReview()
                : null
)

.testingReview(
        engineering != null
                ? engineering.getTestingReview()
                : null
)

.deploymentReview(
        engineering != null
                ? engineering.getDeploymentReview()
                : null
)

.maturityStage(
        engineering != null
                ? engineering.getMaturityStage()
                : null
)

.maturityScore(
        engineering != null
                ? engineering.getMaturityScore()
                : null
)

.repositoryConfidence(
    engineering != null
        ? engineering.getEvidenceCoverage()
        : null
)

.repositoryConfidenceScore(
        engineering != null
                ? engineering.getEvidenceCoverageScore()
                : null
)

.hiringRecommendation(
        engineering != null
                ? engineering.getHiringRecommendation()
                : null
)

.portfolioRecommendation(
        engineering != null
                ? engineering.getPortfolioRecommendation()
                : null
)

.finalEngineeringReview(
        engineering != null
                ? engineering.getFinalEngineeringReview()
                : null
)
.overallCareerScore(
        career != null
                ? career.getOverallCareerScore()
                : null
)

.researchImpact(
        career != null
                ? career.getResearchImpact()
                : null
)

.startupImpact(
        career != null
                ? career.getStartupImpact()
                : null
)

.openSourceImpact(
        career != null
                ? career.getOpenSourceImpact()
                : null
)

.industryDemand(
        career != null
                ? career.getIndustryDemand()
                : null
)

.hiringSignal(
        career != null
                ? career.getHiringSignal()
                : null
)

.confidence(
        career != null
                ? career.getConfidence()
                : null
)

.overallCareerVerdict(
        career != null
                ? career.getOverallCareerVerdict()
                : null
)

.analyzedAt(
        project.getAnalyzedAt()
)

.analysisVersion(
        project.getAnalysisVersion()
)

.analysisStatus(
        project.getAnalysisStatus()
)

            .build();

}


    private String[] extractOwnerAndRepo(
        String githubUrl
) {

    String url = githubUrl
            .replace("https://github.com/", "")
            .replace("http://github.com/", "")
            .replaceAll("/$", "");

    String[] parts = url.split("/");

    if (parts.length < 2) {

        throw new RuntimeException(
                "Invalid GitHub URL."
        );

    }

    return new String[] {
            parts[0],
            parts[1]
    };

}

@SuppressWarnings("unchecked")
private List<String> extractLanguages(
        Map<String, Object> repository
) {

    Map<String, Object> languages =
            (Map<String, Object>) repository.get("languages");

    if (languages == null) {

        return List.of();

    }

    List<Map<String, Object>> edges =
            (List<Map<String, Object>>) languages.get("edges");

    return edges.stream()

            .map(edge ->

                    (Map<String, Object>)
                            edge.get("node")

            )

            .map(node ->

                    node.get("name")
                            .toString()

            )

            .toList();

}
   
}