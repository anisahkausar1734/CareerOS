package com.careeros.careeros_backend.service.projectanalysis.career.scoring;

import com.careeros.careeros_backend.dto.ProfileResponse;
import com.careeros.careeros_backend.dto.projectanalysis.ProjectEngineeringAnalysis;

public interface CareerScoreCalculator {

    Integer calculate(

            ProjectEngineeringAnalysis engineering,

            ProfileResponse profile

    );

}