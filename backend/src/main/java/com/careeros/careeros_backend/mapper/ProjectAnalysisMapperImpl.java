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

    // Save AI analysis
    project.setEngineeringAnalysis(
            analysis.getEngineering()
    );

    project.setCareerImpact(
            analysis.getCareer()
    );

    // Analysis metadata
    project.setAnalysisStatus("COMPLETED");

    project.setAnalysisVersion(
            project.getAnalysisVersion() == null
                    ? 1
                    : project.getAnalysisVersion() + 1
    );

    if (analysis.getMetadata() != null) {

        project.setAnalyzedAt(
                analysis.getMetadata().getAnalyzedAt() != null
                        ? analysis.getMetadata().getAnalyzedAt()
                        : LocalDateTime.now()
        );

    } else {

        project.setAnalyzedAt(
                LocalDateTime.now()
        );

    }

    project.setUpdatedAt(
            LocalDateTime.now()
    );
}

}