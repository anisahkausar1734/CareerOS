package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.YearReadinessResponse;
import com.careeros.careeros_backend.service.YearExpectationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/career")
@RequiredArgsConstructor
public class YearReadinessController {

    private final YearExpectationService
            yearExpectationService;

    @GetMapping("/year-readiness/{email}")
    public YearReadinessResponse getYearReadiness(
            @PathVariable String email
    ) {

        return yearExpectationService
                .getYearReadiness(email);
    }
}