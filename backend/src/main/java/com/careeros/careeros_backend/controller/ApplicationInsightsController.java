package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.ApplicationInsightsResponse;
import com.careeros.careeros_backend.service.ApplicationInsightsService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/application-insights")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ApplicationInsightsController {

    private final ApplicationInsightsService
            applicationInsightsService;

    @PostMapping("/{email}")
    public ApplicationInsightsResponse analyze(
            @PathVariable String email
    ) {

        return applicationInsightsService
                .analyze(email);
    }
}