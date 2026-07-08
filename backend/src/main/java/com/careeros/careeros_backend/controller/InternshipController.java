package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.JobRecommendationResponse;
import com.careeros.careeros_backend.dto.JobSearchRequest;
import com.careeros.careeros_backend.service.InternshipRecommendationService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internships")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InternshipController {

    private final InternshipRecommendationService
            internshipRecommendationService;

    @GetMapping("/recommend/{email}")
    public JobRecommendationResponse
    getRecommendations(
            @PathVariable
            String email
    ) {

        return internshipRecommendationService
                .getInternships(
                        email
                );
    }

    @PostMapping("/search")
    public JobRecommendationResponse
    search(
            @RequestBody
            JobSearchRequest request
    ) {

        return internshipRecommendationService
                .searchJobs(
                        request
                );
    }
}