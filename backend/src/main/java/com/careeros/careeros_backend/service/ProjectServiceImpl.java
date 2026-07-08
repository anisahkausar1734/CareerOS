package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.CreateProjectRequest;
import com.careeros.careeros_backend.dto.GithubInspectionResponse;
import com.careeros.careeros_backend.dto.ProjectAnalysisResponse;
import com.careeros.careeros_backend.dto.ProjectIntelligenceResponse;
import com.careeros.careeros_backend.dto.ProjectResponse;
import com.careeros.careeros_backend.model.Project;
import com.careeros.careeros_backend.model.StudentProfile;
import com.careeros.careeros_backend.repository.ProjectRepository;
import com.careeros.careeros_backend.repository.RoadmapRepository;
import com.careeros.careeros_backend.repository.StudentProfileRepository;
import com.careeros.careeros_backend.dto.ProjectIntelligenceResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl
        implements ProjectService {

    private final ProjectRepository projectRepository;

    private final GeminiService geminiService;

    private final GithubInspectionService
        githubInspectionService;

    private final StudentProfileRepository
        studentProfileRepository;    

        private final RoadmapRepository
        roadmapRepository;

   private int calculateGithubScore(
        GithubInspectionResponse github
)
{

    int score = 0;

    if(Boolean.TRUE.equals(
            github.getHasFrontend()
    ))
    {
        score += 10;
    }

    if(Boolean.TRUE.equals(
            github.getHasBackend()
    ))
    {
        score += 15;
    }

    if(Boolean.TRUE.equals(
            github.getHasDatabase()
    ))
    {
        score += 10;
    }

    if(Boolean.TRUE.equals(
            github.getHasAuthentication()
    ))
    {
        score += 10;
    }

    if(Boolean.TRUE.equals(
            github.getHasTesting()
    ))
    {
        score += 10;
    }

    if(Boolean.TRUE.equals(
            github.getHasDeployment()
    ))
    {
        score += 10;
    }

    if(Boolean.TRUE.equals(
            github.getHasDocumentation()
    ))
    {
        score += 5;
    }

    if(Boolean.TRUE.equals(
            github.getHasCICD()
    ))
    {
        score += 10;
    }

    if(Boolean.TRUE.equals(
            github.getHasDocker()
    ))
    {
        score += 5;
    }

    if(Boolean.TRUE.equals(
            github.getHasJwt()
    ))
    {
        score += 5;
    }

    score += Math.min(
            github.getControllerCount() == null
                    ? 0
                    : github.getControllerCount(),
            5
    );

    score += Math.min(
            github.getServiceCount() == null
                    ? 0
                    : github.getServiceCount(),
            5
    );

    score += Math.min(
            github.getRepositoryCount() == null
                    ? 0
                    : github.getRepositoryCount(),
            5
    );

    score += Math.min(
            github.getDtoCount() == null
                    ? 0
                    : github.getDtoCount(),
            5
    );

    return Math.min(
            score,
            100
    );
}

@Override
public ProjectIntelligenceResponse
getProjectIntelligence(
        String email
)
{

    List<Project> projects =
            projectRepository
                    .findByEmail(email);

    if(projects.isEmpty())
    {
        return ProjectIntelligenceResponse
                .builder()
                .overallProjectScore(0)
                .averageEngineeringQuality(0)
                .averageRoleAlignment(0)
                .averageProductionReadiness(0)
                .averageResumeImpact(0)
                .averageInternshipImpact(0)
                .averageJobImpact(0)
                .projectCount(0)
                .bestProject("No Projects")
                .build();
    }

  int overallProjectScore =

        (int)

        projects.stream()

                .filter(
                        project ->
                                project.getProjectScore()
                                != null
                )

                .mapToInt(
                        Project::getProjectScore
                )

                .average()

                .orElse(0);

    int averageEngineeringQuality =

            (int)

           projects.stream()

        .filter(
                project ->
                        project.getEngineeringQuality()
                        != null
        )

        .mapToInt(
                Project::getEngineeringQuality
        )

        .average()

                    .orElse(0);

    int averageRoleAlignment =

            (int)

           projects.stream()

        .filter(
                project ->
                        project.getRoleAlignment()
                        != null
        )

        .mapToInt(
                Project::getRoleAlignment
        )

        .average()

                    .orElse(0);

    int averageProductionReadiness =

            (int)

           projects.stream()

        .filter(
                project ->
                        project.getProductionReadiness()
                        != null
        )

        .mapToInt(
                Project::getProductionReadiness
        )

        .average()

                    .orElse(0);

    int averageResumeImpact =

            (int)

          projects.stream()

        .filter(
                project ->
                        project.getResumeImpact()
                        != null
        )

        .mapToInt(
                Project::getResumeImpact
        )

        .average()

                    .orElse(0);

    int averageInternshipImpact =

            (int)

         projects.stream()

        .filter(
                project ->
                        project.getInternshipImpact()
                        != null
        )

        .mapToInt(
                Project::getInternshipImpact
        )

        .average()

                    .orElse(0);

    int averageJobImpact =

            (int)

          projects.stream()

        .filter(
                project ->
                        project.getJobImpact()
                        != null
        )

        .mapToInt(
                Project::getJobImpact
        )

        .average()

                    .orElse(0);

    Project bestProject =

            projects.stream()

                    .max(
                            java.util.Comparator.comparingInt(
                                    project ->
                                            project.getProjectScore() == null
                                                    ? 0
                                                    : project.getProjectScore()
                            )
                    )

                    .orElse(null);

    return ProjectIntelligenceResponse
            .builder()
            .overallProjectScore(
                    overallProjectScore
            )
            .averageEngineeringQuality(
                    averageEngineeringQuality
            )
            .averageRoleAlignment(
                    averageRoleAlignment
            )
            .averageProductionReadiness(
                    averageProductionReadiness
            )
            .averageResumeImpact(
                    averageResumeImpact
            )
            .averageInternshipImpact(
                    averageInternshipImpact
            )
            .averageJobImpact(
                    averageJobImpact
            )
            .projectCount(
                    projects.size()
            )
            .bestProject(
                    bestProject == null
                            ? "N/A"
                            : bestProject.getProjectName()
            )
            .build();
}


    @Override
    public ProjectResponse createProject(
            CreateProjectRequest request
    ) {

        Project project =
                Project.builder()
                        .email(
                                request.getEmail()
                        )
                        .projectName(
                                request.getProjectName()
                        )
                        .description(
                                request.getDescription()
                        )
                        .techStack(
                                request.getTechStack()
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



                        
        project =


                projectRepository.save(
                        project
                );


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

    Project project =
            projectRepository
                    .findById(projectId)
                    .orElseThrow(
                            () ->
                                    new RuntimeException(
                                            "Project not found"
                                    )
                    );

StudentProfile profile =
        studentProfileRepository
                .findByEmail(
                        project.getEmail()
                )
                .orElseThrow(
                        () -> new RuntimeException(
                                "Profile not found"
                        )
                );

String dreamRole =
        profile.getDreamRole();


GithubInspectionResponse github = null;

try {

    github =
            githubInspectionService
                    .inspectRepository(
                            project.getGithubUrl()
                    );

} catch (Exception e) {

    System.out.println(
            "GitHub inspection failed: "
            + e.getMessage()
    );
}

                int githubScore =
        calculateGithubScore(
                github
        );

    String prompt =
            """
            You are a senior software engineering recruiter.

            Analyze this project.

           PROJECT NAME:
%s

DESCRIPTION:
%s

TECH STACK:
%s

GITHUB ANALYSIS:

Frontend:
%s

Backend:
%s

Database:
%s

Authentication:
%s

Testing:
%s

Deployment:
%s

Documentation:
%s

GitHub Score:
%d

Dream Role:
%s

Evaluate and return:

TECHNICAL_COMPLEXITY=0-100

CODE_QUALITY=0-100

ARCHITECTURE_SCORE=0-100

ENGINEERING_QUALITY=0-100

PRODUCTION_READINESS=0-100

DEPLOYMENT_SCORE=0-100

ROLE_ALIGNMENT=0-100

INDUSTRY_VALUE=0-100

ROLE_ALIGNMENT=0-100

INDUSTRY_VALUE=0-100

PROJECT_SCORE=0-100

RESUME_IMPACT=0-100

INTERNSHIP_IMPACT=0-100

JOB_IMPACT=0-100

            VERDICT=text

            STRENGTHS=item1|item2|item3

            WEAKNESSES=item1|item2|item3

            IMPROVEMENTS=item1|item2|item3
            """
                    .formatted(
        project.getProjectName(),
        project.getDescription(),
        String.join(
                ", ",
                project.getTechStack()
        ),

        github.getHasFrontend(),
        github.getHasBackend(),
        github.getHasDatabase(),
        github.getHasAuthentication(),
        github.getHasTesting(),
        github.getHasDeployment(),
        github.getHasDocumentation(),

        githubScore,

        dreamRole
);

    String aiResponse =
            geminiService.askGemini(
                    prompt
            );

    System.out.println(
            "========== PROJECT AI =========="
    );

    System.out.println(
            aiResponse
    );

    System.out.println(
            "================================"
    );

project.setGithubScore(
        githubScore
);

   ProjectAnalysisResponse response =
        parseProjectAnalysis(
                aiResponse,
                project
        );

        project.setGithubScore(
        githubScore
);

project.setTechnicalComplexity(
        response.getTechnicalComplexity()
);

project.setEngineeringQuality(
        response.getEngineeringQuality()
);

project.setProductionReadiness(
        response.getProductionReadiness()
);

project.setRoleAlignment(
        response.getRoleAlignment()
);

project.setIndustryValue(
        response.getIndustryValue()
);

project.setProjectScore(
        response.getProjectScore()
);

project.setResumeImpact(
        response.getResumeImpact()
);

project.setInternshipImpact(
        response.getInternshipImpact()
);

project.setJobImpact(
        response.getJobImpact()
);

projectRepository.save(
        project
);

return response;
  
}

private ProjectAnalysisResponse parseProjectAnalysis(
        String response,
        Project project
) {

    Integer projectScore = 0;
    Integer resumeImpact = 0;
    Integer internshipImpact = 0;
    Integer jobImpact = 0;
    Integer technicalComplexity = 0;
Integer codeQuality = 0;
Integer engineeringQuality = 0;
Integer architectureScore = 0;
Integer productionReadiness = 0;
Integer deploymentScore = 0;
Integer roleAlignment = 0;

Integer industryValue = 0;

    String verdict = "";
    

    
    List<String> strengths =
            new ArrayList<>();

    List<String> weaknesses =
            new ArrayList<>();

    List<String> improvements =
            new ArrayList<>();

    for (
            String line :
            response.split("\n")
    ) {
        
        if (
                line.startsWith(
                        "PROJECT_SCORE="
                )
        ) {

            projectScore =
                    Integer.parseInt(
                            line.replace(
                                    "PROJECT_SCORE=",
                                    ""
                            ).trim()
                    );
        }
 else if (
        line.startsWith(
                "DEPLOYMENT_SCORE="
        )
) {

    deploymentScore =
            Integer.parseInt(
                    line.replace(
                            "DEPLOYMENT_SCORE=",
                            ""
                    ).trim()
            );
}       
else if (
        line.startsWith(
                "TECHNICAL_COMPLEXITY="
        )
) {

    technicalComplexity =
            Integer.parseInt(
                    line.replace(
                            "TECHNICAL_COMPLEXITY=",
                            ""
                    ).trim()
            );
}
else if (
        line.startsWith(
                "ARCHITECTURE_SCORE="
        )
) {

    architectureScore =
            Integer.parseInt(
                    line.replace(
                            "ARCHITECTURE_SCORE=",
                            ""
                    ).trim()
            );
}

else if (
        line.startsWith(
                "ENGINEERING_QUALITY="
        )
) {

    engineeringQuality =
            Integer.parseInt(
                    line.replace(
                            "ENGINEERING_QUALITY=",
                            ""
                    ).trim()
            );
}

else if (
        line.startsWith(
                "PRODUCTION_READINESS="
        )
) {

    productionReadiness =
            Integer.parseInt(
                    line.replace(
                            "PRODUCTION_READINESS=",
                            ""
                    ).trim()
            );
}

else if (
        line.startsWith(
                "CODE_QUALITY="
        )
) {

    codeQuality =
            Integer.parseInt(
                    line.replace(
                            "CODE_QUALITY=",
                            ""
                    ).trim()
            );
}

else if (
        line.startsWith(
                "ROLE_ALIGNMENT="
        )
) {

    roleAlignment =
            Integer.parseInt(
                    line.replace(
                            "ROLE_ALIGNMENT=",
                            ""
                    ).trim()
            );
}

else if (
        line.startsWith(
                "INDUSTRY_VALUE="
        )
) {

    industryValue =
            Integer.parseInt(
                    line.replace(
                            "INDUSTRY_VALUE=",
                            ""
                    ).trim()
            );
}



        else if (
                line.startsWith(
                        "RESUME_IMPACT="
                )
        ) {

            resumeImpact =
                    Integer.parseInt(
                            line.replace(
                                    "RESUME_IMPACT=",
                                    ""
                            ).trim()
                    );
        }

        else if (
                line.startsWith(
                        "INTERNSHIP_IMPACT="
                )
        ) {

            internshipImpact =
                    Integer.parseInt(
                            line.replace(
                                    "INTERNSHIP_IMPACT=",
                                    ""
                            ).trim()
                    );
        }

        else if (
                line.startsWith(
                        "JOB_IMPACT="
                )
        ) {

            jobImpact =
                    Integer.parseInt(
                            line.replace(
                                    "JOB_IMPACT=",
                                    ""
                            ).trim()
                    );
        }

        else if (
                line.startsWith(
                        "VERDICT="
                )
        ) {

            verdict =
                    line.replace(
                            "VERDICT=",
                            ""
                    ).trim();
        }

        else if (
                line.startsWith(
                        "STRENGTHS="
                )
        ) {

            strengths =
                    List.of(
                            line.replace(
                                    "STRENGTHS=",
                                    ""
                            ).split("\\|")
                    );
        }

        else if (
                line.startsWith(
                        "WEAKNESSES="
                )
        ) {

            weaknesses =
                    List.of(
                            line.replace(
                                    "WEAKNESSES=",
                                    ""
                            ).split("\\|")
                    );
        }

        else if (
                line.startsWith(
                        "IMPROVEMENTS="
                )
        ) {

            improvements =
                    List.of(
                            line.replace(
                                    "IMPROVEMENTS=",
                                    ""
                            ).split("\\|")
                    );
        }
    }
    

project.setDeploymentScore(
        deploymentScore
);

project.setArchitectureScore(
        architectureScore
);

    project.setCodeQuality(
        codeQuality
);

    project.setProjectScore(
            projectScore
    );

    project.setResumeImpact(
            resumeImpact
    );

    project.setInternshipImpact(
            internshipImpact
    );

    project.setJobImpact(
            jobImpact
    );

    project.setVerdict(
            verdict
    );

    project.setStrengths(
            strengths
    );

    project.setWeaknesses(
            weaknesses
    );

    project.setImprovements(
            improvements
    );
     project.setTechnicalComplexity(
        technicalComplexity
);

project.setEngineeringQuality(
        engineeringQuality
);

project.setProductionReadiness(
        productionReadiness
);

project.setRoleAlignment(
        roleAlignment
);

project.setIndustryValue(
        industryValue
);
    projectRepository.save(
            project
    );



    
    return ProjectAnalysisResponse
            .builder()
            .projectScore(
                    projectScore
            )
            .resumeImpact(
                    resumeImpact
            )
            .internshipImpact(
                    internshipImpact
            )
            .jobImpact(
                    jobImpact
            )
            .verdict(
                    verdict
            )
            .strengths(
                    strengths
            )
            .weaknesses(
                    weaknesses
            )

            .technicalComplexity(
        technicalComplexity
)

.deploymentScore(
        deploymentScore
)
.architectureScore(
        architectureScore
)
.codeQuality(
        codeQuality
)
.engineeringQuality(
        engineeringQuality
)

.productionReadiness(
        productionReadiness
)

.roleAlignment(
        roleAlignment
)

.industryValue(
        industryValue
)
            .improvements(
                    improvements
            )

            .githubScore(
        project.getGithubScore()
)
            .build();
}



    private ProjectResponse mapProject(
            Project project
    ) {

        return ProjectResponse
                .builder()
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
                .projectScore(
                        project.getProjectScore()
                )
                .resumeImpact(
                        project.getResumeImpact()
                )
                .internshipImpact(
                        project.getInternshipImpact()
                )
                .jobImpact(
                        project.getJobImpact()
                )
                .verdict(
                        project.getVerdict()
                )
                .strengths(
                        project.getStrengths()
                )
                .weaknesses(
                        project.getWeaknesses()
                )
                .improvements(
                        project.getImprovements()
                )
.technicalComplexity(
        project.getTechnicalComplexity()
)

.engineeringQuality(
        project.getEngineeringQuality()
)

.productionReadiness(
        project.getProductionReadiness()
)

.roleAlignment(
        project.getRoleAlignment()
)

.industryValue(
        project.getIndustryValue()
)

                .build();
    }

   
}