package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.ProjectAnalysisResponse;
import com.careeros.careeros_backend.dto.ProjectInfo;
import com.careeros.careeros_backend.service.ProjectAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectAnalysisController {

    private final ProjectAnalysisService
            projectAnalysisService;

    @PostMapping("/analyze")
    public ProjectAnalysisResponse analyzeProject(
            @RequestBody ProjectInfo project
    ) {

        return projectAnalysisService
                .analyzeProject(project);
    }
}