package com.careeros.careeros_backend.service.projectanalysis.context;

import com.careeros.careeros_backend.dto.github.RepositoryEvidenceResponse;
import com.careeros.careeros_backend.dto.projectanalysis.context.EngineeringContext;

public interface EngineeringContextBuilder {

    EngineeringContext build(
            RepositoryEvidenceResponse evidence
    );

}