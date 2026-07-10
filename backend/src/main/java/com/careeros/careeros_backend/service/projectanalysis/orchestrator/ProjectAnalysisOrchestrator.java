package com.careeros.careeros_backend.service.projectanalysis.orchestrator;

import com.careeros.careeros_backend.dto.projectanalysis.ProjectAnalysisResponse;

public interface ProjectAnalysisOrchestrator {

    ProjectAnalysisResponse analyze(
            String projectId
    );

}