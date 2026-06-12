package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.CompanyReadinessResponse;
import com.careeros.careeros_backend.service.CompanyReadinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/career")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CompanyReadinessController {

    private final CompanyReadinessService
            companyReadinessService;

    @GetMapping(
            "/company-readiness/{email}"
    )
    public CompanyReadinessResponse
    getReadiness(
            @PathVariable String email
    ) {

        return companyReadinessService
                .getReadiness(email);
    }
}