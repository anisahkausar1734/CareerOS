package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.ProjectRecommendationResponse;
import com.careeros.careeros_backend.service.ProjectRecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectRecommendationController {

    private final
    ProjectRecommendationService
            recommendationService;

    @GetMapping("/recommend/{targetRole}")
    public ProjectRecommendationResponse
    recommendProjects(
            @PathVariable String targetRole
    ) {

        return recommendationService
                .recommendProjects(targetRole);
    }
}