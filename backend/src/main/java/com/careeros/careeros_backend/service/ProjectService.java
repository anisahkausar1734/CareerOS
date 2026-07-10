package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.CreateProjectRequest;
import com.careeros.careeros_backend.dto.projectanalysis.ProjectAnalysisResponse;
import com.careeros.careeros_backend.dto.ProjectIntelligenceResponse;
import com.careeros.careeros_backend.dto.ProjectResponse;
import com.careeros.careeros_backend.dto.ProjectIntelligenceResponse;

import java.util.List;

public interface ProjectService {

    ProjectResponse createProject(
            CreateProjectRequest request
    );

    List<ProjectResponse> getProjects(
            String email
    );

    void deleteProject(
            String projectId
    );
ProjectAnalysisResponse analyzeProject(
        String projectId
);

ProjectIntelligenceResponse
getProjectIntelligence(
        String email
);

}
