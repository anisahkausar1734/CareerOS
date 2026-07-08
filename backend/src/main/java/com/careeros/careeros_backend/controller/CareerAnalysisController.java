package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.CareerAnalysisResponse;
import com.careeros.careeros_backend.service.CareerAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/career-analysis")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CareerAnalysisController {

    private final CareerAnalysisService
            careerAnalysisService;

    @GetMapping("/{email}")
    public CareerAnalysisResponse analyze(
            @PathVariable
            String email
    ) {

        return careerAnalysisService
                .analyzeCareer(
                        email
                );
    }
}