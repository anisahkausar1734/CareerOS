package com.careeros.careeros_backend.service.github.analysis;

import com.careeros.careeros_backend.dto.projectanalysis.ProjectAnalysisResponse;
import com.careeros.careeros_backend.dto.github.RepositoryEvidenceResponse;

public interface EngineeringAnalysisService {

    ProjectAnalysisResponse analyze(
            RepositoryEvidenceResponse evidence
    );

}