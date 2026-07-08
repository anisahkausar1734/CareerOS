package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.ProjectBlueprintResponse;
import com.careeros.careeros_backend.service.ProjectBlueprintService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects/blueprint")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProjectBlueprintController {

    private final ProjectBlueprintService
            projectBlueprintService;

    @GetMapping("/{projectName}")
    public ProjectBlueprintResponse
    getBlueprint(
            @PathVariable
            String projectName
    )
    {
        return projectBlueprintService
                .getBlueprint(
                        projectName
                );
    }

    @PostMapping("/regenerate/{projectName}")
    public ProjectBlueprintResponse
    regenerateBlueprint(
            @PathVariable
            String projectName
    )
    {
        return projectBlueprintService
                .regenerateBlueprint(
                        projectName
                );
    }
}