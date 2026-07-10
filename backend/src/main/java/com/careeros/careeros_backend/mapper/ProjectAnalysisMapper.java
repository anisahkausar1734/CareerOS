package com.careeros.careeros_backend.mapper;

import com.careeros.careeros_backend.dto.projectanalysis.ProjectAnalysisResponse;
import com.careeros.careeros_backend.model.Project;

public interface ProjectAnalysisMapper {

    void updateProject(

            Project project,

            ProjectAnalysisResponse analysis

    );

}