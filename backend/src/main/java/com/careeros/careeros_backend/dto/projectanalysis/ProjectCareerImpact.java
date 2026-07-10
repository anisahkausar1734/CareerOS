package com.careeros.careeros_backend.dto.projectanalysis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCareerImpact {

    /**
     * Why this analysis is being performed.
     */
    private AnalysisPurpose analysisPurpose;

    /**
     * Overall career impact score (0-100)
     * after considering the student's profile.
     */
    private Integer overallCareerScore;

    /**
     * Resume value for this purpose.
     */
    private Integer resumeImpact;

    /**
     * Internship readiness.
     */
    private Integer internshipImpact;

    /**
     * Full-time job readiness.
     */
    private Integer jobImpact;

    /**
     * Research suitability.
     */
    private Integer researchImpact;

    /**
     * Startup / product building value.
     */
    private Integer startupImpact;

    /**
     * Open-source contribution relevance.
     */
    private Integer openSourceImpact;

    /**
     * Alignment with the student's target role.
     */
    private Integer roleAlignment;

    /**
     * Current industry demand for projects like this.
     */
    private Integer industryDemand;

    /**
     * Hiring signal for recruiters.
     */
    private Integer hiringSignal;

    /**
     * Confidence in this career assessment.
     */
    private Integer confidence;

    /**
     * Final personalized verdict.
     */
    private String overallCareerVerdict;

}