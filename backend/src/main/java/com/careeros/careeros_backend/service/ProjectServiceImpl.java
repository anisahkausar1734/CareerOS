package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.CreateProjectRequest;
import com.careeros.careeros_backend.dto.GithubInspectionResponse;
import com.careeros.careeros_backend.dto.projectanalysis.ProjectAnalysisResponse;
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
public ProjectIntelligenceResponse getProjectIntelligence(
        String email
) {

    throw new UnsupportedOperationException(
            "Project Intelligence V2 is currently being migrated."
    );

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

    throw new UnsupportedOperationException(
            "Project Analysis V2 is currently being migrated."
    );

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
    



    
    return ProjectAnalysisResponse
            .builder()
            



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

                .build();
    }

   
}