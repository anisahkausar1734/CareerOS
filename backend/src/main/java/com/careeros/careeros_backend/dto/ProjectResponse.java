package com.careeros.careeros_backend.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ProjectResponse {

    private String id;

    private String projectName;

    private String description;

    private List<String> techStack;

    private String githubUrl;

    private String liveUrl;

    private Integer projectScore;

    private Integer technicalComplexity;

private Integer engineeringQuality;

private Integer productionReadiness;

private Integer roleAlignment;

private Integer industryValue;

private Integer codeQuality;

private Integer architectureScore;

private Integer documentationQuality;

private Integer securityScore;

private Integer testingQuality;

private Integer deploymentReadiness;

private Integer repositoryConfidenceScore;

private Integer scalability;

private String engineeringReview;

private String architectureReview;

private String documentationReview;

private String securityReview;

private String testingReview;

private String deploymentReview;

private String maturityStage;

private Integer maturityScore;

private String repositoryConfidence;

private Integer overallCareerScore;

private Integer researchImpact;

private Integer startupImpact;

private Integer openSourceImpact;

private LocalDateTime analyzedAt;

private Integer analysisVersion;

private String analysisStatus;

private Integer industryDemand;

private Integer hiringSignal;

private Integer confidence;

private String overallCareerVerdict;

private String hiringRecommendation;

private String portfolioRecommendation;

private String finalEngineeringReview;

    private Integer resumeImpact;

    private Integer internshipImpact;

    private Integer jobImpact;

    private String verdict;

    private List<String> strengths;

    private List<String> weaknesses;

    private List<String> improvements;
}