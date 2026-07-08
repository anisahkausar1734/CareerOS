package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.JobMatchResponse;
import com.careeros.careeros_backend.service.AIJobMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job-match")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AIJobMatchController {

    private final AIJobMatchService aiJobMatchService;

    @PostMapping
    public JobMatchResponse analyzeJob(

            @RequestParam String email,

            @RequestParam String jobTitle
    ) {

        return aiJobMatchService
                .analyzeJob(
                        email,
                        jobTitle
                );
    }
}