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
public class ResumeAnalysisResponse {
// Basic Context
private String dreamRole;
private String currentYear;

// Objective Metrics
private Integer resumeScore;
private Integer atsScore;
private Integer roleAlignmentScore;
private Integer skillsCoverageScore;
private Integer projectStrengthScore;
private Integer internshipReadiness;
private Integer jobReadiness;

// Executive Intelligence
private String executiveSummary;
private String overallVerdict;
private String confidenceLevel;

// Resume Identity
private String currentResumeIdentity;
private String desiredIdentity;
private Integer identityAlignmentScore;
private String identityGap;

// Stage Evaluation
private String currentAcademicStage;
private String expectedResumeLevel;
private String currentResumeLevel;
private String stageEvaluation;

// Resume Insights
private List<String> topStrengths;
private List<String> resumeGaps;
private List<String> keyInsights;
private String hiddenPotential;
private String resumeNarrative;

// Recruiter Perspective
private String recruiterFirstImpression;
private String recruiterOpinion;
private String recruiterConfidence;

// Resume Potential
private String growthPotential;
private Integer potentialResumeScore;
private String careerProjection;

// Missing Resume Components
private List<String> missingResumeComponents;

// Resume Communication
private String resumeConfidence;

// Next Career Milestone
private String nextMilestone;
private String guidePreview;
private Boolean readyForResumeGuide;

// Metadata
private String analysisVersion;
private String oneLineResumeVerdict;
}