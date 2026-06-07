package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.CompanyReadinessResponse;
import com.careeros.careeros_backend.service.CompanyReadinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/career")
@RequiredArgsConstructor
public class CompanyReadinessController {

    private final CompanyReadinessService
            companyReadinessService;

    @GetMapping(
            "/company-readiness/google/{email}"
    )
    public CompanyReadinessResponse
    getGoogleReadiness(
            @PathVariable String email
    ) {

        return companyReadinessService
                .getGoogleReadiness(email);
    }
}