package com.careeros.careeros_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoadmapResponse {

    private String dreamRole;

    private Integer estimatedMonths;

    private Integer readinessScore;

    private List<RoadmapStepResponse> roadmapSteps;

    private Integer resumeScore;

    private Integer atsScore;

    private Integer internshipReadiness;

    private Integer jobReadiness;

    private List<String> topPrioritySkills;

    private String nextAction;

    private String actionReason;

    private List<String> recommendedProjects;

    private Integer totalWeeks;

    private String expectedOutcome;

    private Integer completionPercentage;
}