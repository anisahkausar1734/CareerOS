package com.careeros.careeros_backend.service.projectanalysis.ai;

import com.careeros.careeros_backend.dto.projectanalysis.context.EngineeringContext;

public interface EngineeringPromptBuilder {

    String buildPrompt(
            EngineeringContext context
    );

}