package com.careeros.careeros_backend.service.projectanalysis.ai;

import com.careeros.careeros_backend.dto.projectanalysis.ProjectAnalysisResponse;

public interface EngineeringResponseParser {

    ProjectAnalysisResponse parse(
            String geminiResponse
    );

}