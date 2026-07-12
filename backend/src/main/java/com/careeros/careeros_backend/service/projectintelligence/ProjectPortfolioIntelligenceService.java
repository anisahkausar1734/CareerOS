package com.careeros.careeros_backend.service.projectintelligence;

import com.careeros.careeros_backend.dto.ProjectIntelligenceResponse;

public interface ProjectPortfolioIntelligenceService {

    ProjectIntelligenceResponse generate(
            String email
    );

}