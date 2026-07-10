package com.careeros.careeros_backend.service.projectanalysis.orchestrator;

import com.careeros.careeros_backend.dto.ProfileResponse;
import com.careeros.careeros_backend.dto.projectanalysis.AnalysisPurpose;
import com.careeros.careeros_backend.dto.projectanalysis.ProjectAnalysisResponse;
import com.careeros.careeros_backend.dto.projectanalysis.context.EngineeringContext;
import com.careeros.careeros_backend.dto.github.RepositoryEvidenceResponse;
import com.careeros.careeros_backend.mapper.ProfileMapper;
import com.careeros.careeros_backend.mapper.ProjectAnalysisMapper;
import com.careeros.careeros_backend.model.Project;
import com.careeros.careeros_backend.model.StudentProfile;
import com.careeros.careeros_backend.repository.ProjectRepository;
import com.careeros.careeros_backend.repository.StudentProfileRepository;
import com.careeros.careeros_backend.service.github.cache.RepositoryCacheService;
import com.careeros.careeros_backend.service.projectanalysis.ai.EngineeringAnalysisService;
import com.careeros.careeros_backend.service.projectanalysis.career.CareerImpactService;
import com.careeros.careeros_backend.service.projectanalysis.context.EngineeringContextBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectAnalysisOrchestratorImpl
        implements ProjectAnalysisOrchestrator {

    private final ProjectRepository projectRepository;

    private final StudentProfileRepository studentProfileRepository;

    private final RepositoryCacheService repositoryCacheService;

    private final EngineeringContextBuilder engineeringContextBuilder;

    private final EngineeringAnalysisService engineeringAnalysisService;

    private final CareerImpactService careerImpactService;

    private final ProfileMapper profileMapper;

    private final ProjectAnalysisMapper projectAnalysisMapper;

    @Override
    public ProjectAnalysisResponse analyze(
            String projectId
    ) {

        throw new UnsupportedOperationException(
                "Will implement next."
        );

    }

}