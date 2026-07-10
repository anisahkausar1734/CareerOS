package com.careeros.careeros_backend.service.projectanalysis.career.scoring;

import com.careeros.careeros_backend.dto.ProfileResponse;
import com.careeros.careeros_backend.dto.projectanalysis.ProjectEngineeringAnalysis;
import org.springframework.stereotype.Service;

@Service
public class RoleAlignmentCalculatorImpl
        implements RoleAlignmentCalculator {

    @Override
    public Integer calculate(
            ProjectEngineeringAnalysis engineering,
            ProfileResponse profile
    ) {

        return engineering.getBusinessValue();

    }

}