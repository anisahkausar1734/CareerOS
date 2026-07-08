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
public class SkillGapResponse {

    /* ======================================================
                    EXISTING FIELDS
       ====================================================== */

    private String targetRole;

    private List<String> currentSkills;

    private List<String> missingSkills;

    private Integer skillMatchPercentage;

    private Integer readinessScore;

    private List<String> prioritySkills;

    private String careerPosition;

private String currentStage;

private String timeToJobReady;

private List<LearningStep> learningOrder;

private List<String> topInsights;

private List<String> focusNow;

private List<String> employerExpectations;

private List<String> commonMistakes;

private List<String> industryAdvice;

private List<String> recommendedResources;



    /* ======================================================
                    NEW CAREER INTELLIGENCE
       ====================================================== */

  

    // Core foundation skills
    private List<String> coreSkills;

    // Skills to learn later
    private List<String> advancedSkills;

    private List<String> foundationSkills;

    // Beginner projects
    private List<String> recommendedProjects;

    // Certifications
    private List<String> recommendedCertifications;

   private String currentYear;

private Integer graduationYear;

    // Resume awareness
    private Boolean resumeConsidered;
}