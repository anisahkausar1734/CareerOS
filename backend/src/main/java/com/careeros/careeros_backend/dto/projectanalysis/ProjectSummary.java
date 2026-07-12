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

    /*
     * Basic Project Details
     */
    private String projectName;

    private String projectType;

    private String domain;

    /*
     * Business Understanding
     */
    private String problemSolved;

    private String targetUsers;

    /*
     * Difficulty
     */
    private String estimatedDifficulty;

    private String estimatedDevelopmentTime;

    /*
     * Overall Summary
     */
    private String overallSummary;

}