package com.careeros.careeros_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAnalysisResponse {

    private String projectName;

    private Integer complexityScore;

    private Integer technicalScore;

    private Integer architectureScore;

    private Integer deploymentScore;

    private Integer impactScore;

    private Integer innovationScore;

    private Integer collaborationScore;

    private Integer finalScore;

    private String feedback;
}
