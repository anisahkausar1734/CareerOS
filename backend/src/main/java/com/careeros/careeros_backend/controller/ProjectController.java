package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.CreateProjectRequest;
import com.careeros.careeros_backend.dto.ProjectIntelligenceResponse;
import com.careeros.careeros_backend.dto.ProjectResponse;
import com.careeros.careeros_backend.dto.projectanalysis.ProjectAnalysisResponse;
import com.careeros.careeros_backend.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/create-and-analyze")
public ProjectAnalysisResponse createAndAnalyze(
        @RequestBody CreateProjectRequest request
) {
    return projectService.createAndAnalyze(request);
}

    @PostMapping
    public ProjectResponse createProject(
            @RequestBody CreateProjectRequest request
    ) {
        return projectService.createProject(request);
    }

    @PostMapping("/analyze/{projectId}")
    public ProjectAnalysisResponse analyzeProject(
            @PathVariable String projectId
    ) {
        return projectService.analyzeProject(projectId);
    }

    @PostMapping("/{projectId}/reanalyze")
public ProjectAnalysisResponse reAnalyzeProject(
        @PathVariable String projectId
) {
    return projectService.reAnalyzeProject(projectId);
}

    @GetMapping("/intelligence/{email}")
    public ProjectIntelligenceResponse getProjectIntelligence(
            @PathVariable String email
    ) {
        return projectService.getProjectIntelligence(email);
    }

    @GetMapping("/user/{email}")
    public List<ProjectResponse> getProjects(
            @PathVariable String email
    ) {
        return projectService.getProjects(email);
    }

    @GetMapping("/report/{projectId}")
public ProjectResponse getProjectById(
        @PathVariable String projectId
) {
    return projectService.getProjectById(projectId);
}

    @DeleteMapping("/{projectId}")
    public void deleteProject(
            @PathVariable String projectId
    ) {
        projectService.deleteProject(projectId);
    }
}