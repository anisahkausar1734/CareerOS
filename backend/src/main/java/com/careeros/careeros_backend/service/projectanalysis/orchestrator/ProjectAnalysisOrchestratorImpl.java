package com.careeros.careeros_backend.service.projectanalysis.orchestrator;

import com.careeros.careeros_backend.dto.ProfileResponse;
import com.careeros.careeros_backend.dto.github.RepositoryEvidenceResponse;
import com.careeros.careeros_backend.dto.projectanalysis.AnalysisPurpose;
import com.careeros.careeros_backend.dto.projectanalysis.ProjectAnalysisResponse;
import com.careeros.careeros_backend.dto.projectanalysis.context.EngineeringContext;
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

        // Step 1 - Load project
        Project project = loadProject(projectId);

        // Step 2 - Load student profile
        StudentProfile studentProfile =
                loadStudentProfile(project.getEmail());

        // Step 3 - Convert entity -> DTO
        ProfileResponse profile =
                profileMapper.toResponse(studentProfile);

        // Step 4 - Load cached repository evidence
        RepositoryEvidenceResponse evidence =
                loadRepositoryEvidence(project);

        // Step 5 - Build engineering context
        EngineeringContext context =
                engineeringContextBuilder.build(evidence);

        // Step 6 - AI engineering analysis
        ProjectAnalysisResponse analysis =
                engineeringAnalysisService.analyze(context);

        // Step 7 - Career impact
        enrichCareerImpact(
                analysis,
                profile
        );

        // Step 8 - Persist analysis
        saveAnalysis(
                project,
                analysis
        );

        // Step 9 - Return response
        return analysis;
    }

    private Project loadProject(
            String projectId
    ) {

        return projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new RuntimeException("Project not found."));
    }

    private StudentProfile loadStudentProfile(
            String email
    ) {

        return studentProfileRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Student profile not found."));
    }

    private RepositoryEvidenceResponse loadRepositoryEvidence(
            Project project
    ) {

System.out.println(">>>>>>>>>> RepositoryCacheService CALLED <<<<<<<<<<");

         return repositoryCacheService.getRepositoryEvidence(
            project.getGithubUrl(),
            false
    );

    }

    private void enrichCareerImpact(
        ProjectAnalysisResponse analysis,
        ProfileResponse profile
) {

    analysis.setCareer(

            careerImpactService.evaluate(

                    analysis.getEngineering(),

                    profile,

                    AnalysisPurpose.INTERNSHIP

            )

    );

}

private void saveAnalysis(
        Project project,
        ProjectAnalysisResponse analysis
) {

    projectAnalysisMapper.updateProject(
            project,
            analysis
    );

    projectRepository.save(
            project
    );

}

}