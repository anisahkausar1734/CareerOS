package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.ProjectBlueprintResponse;

public interface ProjectBlueprintService {

    ProjectBlueprintResponse getBlueprint(
            String projectName
    );

    ProjectBlueprintResponse regenerateBlueprint(
            String projectName
    );

}

