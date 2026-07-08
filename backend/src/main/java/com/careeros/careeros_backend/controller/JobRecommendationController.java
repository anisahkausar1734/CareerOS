package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.JobRecommendationResponse;
import com.careeros.careeros_backend.dto.JobSearchRequest;
import com.careeros.careeros_backend.service.JobRecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class JobRecommendationController {

    private final JobRecommendationService
            jobRecommendationService;

    @PostMapping("/search")
    public JobRecommendationResponse searchJobs(
            @RequestBody JobSearchRequest request
    ) {

        return jobRecommendationService
                .searchJobs(request);
    }
}