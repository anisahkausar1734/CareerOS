package com.careeros.careeros_backend.service.projectanalysis.career.scoring;

import com.careeros.careeros_backend.dto.projectanalysis.ProjectEngineeringAnalysis;

public interface HiringSignalCalculator {

    Integer calculate(
            ProjectEngineeringAnalysis engineering
    );

}