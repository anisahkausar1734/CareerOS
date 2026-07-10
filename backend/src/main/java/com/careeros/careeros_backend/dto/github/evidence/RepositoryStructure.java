package com.careeros.careeros_backend.dto.github.evidence;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryStructure {

    private Integer totalFiles;

    private Integer totalDirectories;

    private List<String> topLevelDirectories;

    private List<String> importantFiles;

    private List<String> configurationFiles;

    private List<String> workflowFiles;

    private List<String> documentationFiles;

    private List<String> deploymentFiles;

    private List<String> buildFiles;

    private List<String> dependencyFiles;

}