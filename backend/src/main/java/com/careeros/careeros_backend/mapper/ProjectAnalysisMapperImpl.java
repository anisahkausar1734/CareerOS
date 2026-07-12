package com.careeros.careeros_backend.mapper;

import com.careeros.careeros_backend.dto.projectanalysis.ProjectAnalysisResponse;
import com.careeros.careeros_backend.model.Project;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProjectAnalysisMapperImpl
        implements ProjectAnalysisMapper {

    @Override
    public void updateProject(
            Project project,
            ProjectAnalysisResponse analysis
    ) {

        project.setEngineeringAnalysis(
                analysis.getEngineering()
        );

        project.setCareerImpact(
                analysis.getCareer()
        );

        if (analysis.getMetadata() != null) {

            project.setRepositoryFingerprint(
                    null
            );

            project.setAnalysisVersion(
                    analysis.getMetadata()
                            .getPromptVersion()
            );

            project.setAnalyzedAt(
                    analysis.getMetadata()
                            .getAnalyzedAt()
            );

        }
        else {

            project.setAnalyzedAt(
                    LocalDateTime.now()
            );

        }

        project.setUpdatedAt(
                LocalDateTime.now()
        );

    }

}