package com.careeros.careeros_backend.dto.projectanalysis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAnalysisResponse {

    /**
 * Project identifier.
 */
private String projectId;

/**
 * Project name.
 */
private String projectName;

/**
 * GitHub repository URL.
 */
private String githubUrl;

    /**
     * High-level project overview.
     */
    private ProjectSummary summary;

    /**
     * Engineering intelligence generated
     * from GitHub evidence.
     */
    private ProjectEngineeringAnalysis engineering;

    /**
     * Career impact calculated using
     * the student's profile, target role,
     * graduation year and analysis purpose.
     */
    private ProjectCareerImpact career;

    /**
     * Metadata about this analysis.
     */
    private AnalysisMetadata metadata;

    

}