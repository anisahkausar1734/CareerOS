package com.careeros.careeros_backend.service.projectanalysis.career.scoring;

import com.careeros.careeros_backend.dto.ProfileResponse;
import com.careeros.careeros_backend.dto.projectanalysis.ProjectEngineeringAnalysis;
import org.springframework.stereotype.Service;

@Service
public class CareerScoreCalculatorImpl
        implements CareerScoreCalculator {

    @Override
    public Integer calculate(
            ProjectEngineeringAnalysis engineering,
            ProfileResponse profile
    ) {

        int score = 0;

        score += engineering.getEngineeringScore() * 0.40;

        score += engineering.getProductionReadiness() * 0.15;

        score += engineering.getInnovationScore() * 0.10;

        score += engineering.getTechnicalComplexity() * 0.20;

        score += engineering.getDocumentationQuality() * 0.15;

        return Math.min(score, 100);

    }

}