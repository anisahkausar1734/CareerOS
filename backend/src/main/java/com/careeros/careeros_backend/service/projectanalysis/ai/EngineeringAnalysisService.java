package com.careeros.careeros_backend.service.projectanalysis.ai;

import com.careeros.careeros_backend.dto.projectanalysis.ProjectAnalysisResponse;
import com.careeros.careeros_backend.dto.projectanalysis.context.EngineeringContext;

public interface EngineeringAnalysisService {

    ProjectAnalysisResponse analyze(
            EngineeringContext context
    );

}