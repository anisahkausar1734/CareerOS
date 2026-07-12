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
import com.careeros.careeros_backend.service.projectanalysis.orchestrator.ProjectAnalysisOrchestrator;

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

    return projectAnalysisOrchestrator.analyze(
            projectId
    );

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