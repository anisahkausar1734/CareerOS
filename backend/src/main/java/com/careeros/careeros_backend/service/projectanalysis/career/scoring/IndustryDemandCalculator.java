package com.careeros.careeros_backend.service.projectanalysis.career.scoring;

import com.careeros.careeros_backend.dto.projectanalysis.ProjectEngineeringAnalysis;

public interface IndustryDemandCalculator {

    Integer calculate(
            ProjectEngineeringAnalysis engineering
    );

}