package com.careeros.careeros_backend.dto.projectanalysis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEngineeringAnalysis {

    /*
     * Overall Engineering Assessment
     */
    private Integer engineeringScore;

    private String engineeringLevel;

    private String engineeringVerdict;

    /**
     * Confidence in the engineering analysis (0-100)
     */
private String confidence;
    /*
     * Architecture
     */
    private Integer architectureScore;

    private String architectureStyle;

    private String architectureReview;

    /*
     * Codebase Quality
     */
    private Integer codeQuality;

    private Integer repositoryOrganization;

    private Integer maintainability;

    private String engineeringReview;

    /*
     * Documentation
     */
    private Integer documentationQuality;

    private String documentationReview;

    /*
     * Engineering Practices
     */
    private Integer dependencyManagement;

    private Integer buildQuality;

    private Integer scalability;

    private Integer securityScore;

    private Integer testingQuality;

    private Integer deploymentReadiness;

    private Integer productionReadiness;

    /*
     * Innovation & Business
     */
    private Integer innovationScore;

    private Integer businessValue;

    private Integer technicalComplexity;

    /*
     * AI Reviews
     */
    private String deploymentReview;

    private String scalabilityReview;

    private String securityReview;

    private String testingReview;

    private String innovationReview;

    /*
     * Strengths
     */
    private List<String> strengths;

    /*
     * Engineering Risks
     */
    private List<String> risks;

    /*
     * Missing Engineering Practices
     */
    private List<String> missingEngineeringPractices;

    /*
     * Repository Highlights
     */
    private List<String> engineeringHighlights;

    /*
     * Final Engineering Review
     */
    private String finalEngineeringReview;

    /*
 * Engineering Maturity
 */
private String maturityStage;          // Learning, Portfolio, Professional, Production, Enterprise

private Integer maturityScore;

/*
 * Hiring Signals
 */
private String hiringRecommendation;   // Strong Hire, Hire, Consider, Weak, Reject

private String portfolioRecommendation;

/*
 * Repository Confidence
 */
private String evidenceCoverage;       // Excellent, Good, Moderate, Limited

private Integer evidenceCoverageScore;

}