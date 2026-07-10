package com.careeros.careeros_backend.service.projectanalysis.career.scoring;

import com.careeros.careeros_backend.dto.projectanalysis.ProjectEngineeringAnalysis;
import org.springframework.stereotype.Service;

@Service
public class HiringSignalCalculatorImpl
        implements HiringSignalCalculator {

    @Override
    public Integer calculate(
            ProjectEngineeringAnalysis engineering
    ) {

        int score = 0;

        score += engineering.getEngineeringScore() * 0.35;

        score += engineering.getProductionReadiness() * 0.20;

        score += engineering.getTestingQuality() * 0.15;

        score += engineering.getSecurityScore() * 0.15;

        score += engineering.getDocumentationQuality() * 0.15;

        return Math.min(score, 100);

    }

}