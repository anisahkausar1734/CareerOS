package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.GithubInspectionResponse;
import com.careeros.careeros_backend.service.GithubInspectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/github")
@RequiredArgsConstructor
@CrossOrigin("*")
public class GithubInspectionController {

    private final GithubInspectionService
            githubInspectionService;

    @PostMapping("/inspect")
    public GithubInspectionResponse inspect(
            @RequestParam
            String githubUrl
    ) {

        return githubInspectionService
                .inspectRepository(
                        githubUrl
                );
    }
}