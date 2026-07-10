package com.careeros.careeros_backend.service.projectanalysis.career.scoring;

import com.careeros.careeros_backend.dto.projectanalysis.ProjectEngineeringAnalysis;
import org.springframework.stereotype.Service;

@Service
public class IndustryDemandCalculatorImpl
        implements IndustryDemandCalculator {

    @Override
    public Integer calculate(
            ProjectEngineeringAnalysis engineering
    ) {

        return switch (engineering.getEngineeringLevel()) {

            case "Enterprise Grade" -> 98;

            case "Production Ready" -> 92;

            case "Professional" -> 85;

            case "Portfolio" -> 75;

            default -> 60;

        };

    }

}