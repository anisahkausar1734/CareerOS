package com.careeros.careeros_backend.dto.projectanalysis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectSummary {
 
    private String projectName;

    private String projectType;

    private String domain;

    private String problemSolved;

    private String targetUsers;

    private String estimatedDifficulty;

    private String estimatedDevelopmentTime;

    private String overallSummary;

}