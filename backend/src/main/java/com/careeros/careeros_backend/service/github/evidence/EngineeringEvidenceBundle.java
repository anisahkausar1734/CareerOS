package com.careeros.careeros_backend.service.github.evidence;

import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Data
@Builder
public class EngineeringEvidenceBundle {

    private Map<String,String> documentation;

    private Map<String,String> buildFiles;

    private Map<String,String> dependencyFiles;

    private Map<String,String> configurationFiles;

    private Map<String,String> deploymentFiles;

    private Map<String,String> workflowFiles;

    private Map<String,String> apiFiles;

    private Map<String,String> environmentFiles;

}