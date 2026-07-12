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
public class ProjectIntelligenceResponse {

    /*
     * Portfolio Summary
     */
    private Integer portfolioScore;

    private String portfolioLevel;

    private String portfolioVerdict;

    /*
     * Portfolio Statistics
     */
    private Integer totalProjects;

    private Integer analyzedProjects;

    private Integer productionReadyProjects;

    private Integer deployedProjects;

    private Integer openSourceProjects;

    /*
     * Engineering Intelligence
     */
    private Integer averageEngineeringScore;

    private Integer averageTechnicalComplexity;

    private Integer averageProductionReadiness;

    private Integer averageDocumentationQuality;

    private Integer averageInnovationScore;

    /*
     * Career Intelligence
     */
    private Integer internshipReadiness;

    private Integer resumeStrength;

    private Integer hiringSignal;

    private Integer roleAlignment;

    /*
     * Technology Intelligence
     */
    private List<String> strongestTechnologies;

    private List<String> missingTechnologies;

    /*
     * Portfolio Insights
     */
    private String strongestProject;

    private String recommendedNextProject;

    private String overallRecommendation;

}