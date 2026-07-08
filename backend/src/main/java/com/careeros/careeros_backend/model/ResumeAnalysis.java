package com.careeros.careeros_backend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "resume_analysis")
public class ResumeAnalysis {

    @Id
    private String id;

    private String email;

    /* ==========================================================
                    OBJECTIVE METRICS
       ========================================================== */

    private Integer resumeScore;

    private Integer atsScore;

    private Integer roleAlignmentScore;

    private Integer skillsCoverageScore;

    private Integer projectStrengthScore;

    private Integer internshipReadiness;

    private Integer jobReadiness;

    /* ==========================================================
                    EXECUTIVE INTELLIGENCE
       ========================================================== */

    private String executiveSummary;

    private String overallVerdict;

    private String confidenceLevel;

    /* ==========================================================
                    RESUME IDENTITY
       ========================================================== */

    private String currentResumeIdentity;

    private String desiredIdentity;

    private Integer identityAlignmentScore;

    private String identityGap;

    private String resumeConfidence;


private String oneLineResumeVerdict;

private String analysisVersion;

    /* ==========================================================
                    STAGE EVALUATION
       ========================================================== */

    private String expectedResumeLevel;

    private String currentResumeLevel;

    private String stageEvaluation;

    /* ==========================================================
                    RESUME INSIGHTS
       ========================================================== */

    private List<String> topStrengths;

    private List<String> resumeGaps;

    private List<String> keyInsights;

    private String hiddenPotential;

    private String resumeNarrative;

    /* ==========================================================
                    RECRUITER PERSPECTIVE
       ========================================================== */

    private String recruiterFirstImpression;

    private String recruiterOpinion;

    private String recruiterConfidence;

    /* ==========================================================
                    RESUME POTENTIAL
       ========================================================== */

    private String growthPotential;

    private Integer potentialResumeScore;

    private String careerProjection;

    /* ==========================================================
                    NEXT CAREER MILESTONE
       ========================================================== */

    private String nextMilestone;

    private String guidePreview;

    private Boolean readyForResumeGuide;

    /* ==========================================================
                    MISSING RESUME COMPONENTS
       ========================================================== */

    private List<String> missingResumeComponents;

    /* ==========================================================
                    METADATA
       ========================================================== */

    private LocalDateTime analyzedAt;

}