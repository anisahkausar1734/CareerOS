package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.JobReadinessResponse;
import com.careeros.careeros_backend.service.JobReadinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job-readiness")
@RequiredArgsConstructor
@CrossOrigin("*")
public class JobReadinessController {

    private final JobReadinessService
            jobReadinessService;

    @GetMapping("/{email}")
    public JobReadinessResponse
    getReadiness(
            @PathVariable
            String email
    ) {

        return jobReadinessService
                .getReadiness(
                        email
                );
    }
}