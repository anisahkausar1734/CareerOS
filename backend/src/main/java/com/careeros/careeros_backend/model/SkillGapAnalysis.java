package com.careeros.careeros_backend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.careeros.careeros_backend.dto.LearningStep;

import org.springframework.data.mongodb.core.index.Indexed;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "skill_gap_analysis")
public class SkillGapAnalysis {

    @Id
    private String id;

    @Indexed(unique = true)
private String email;

    private String targetRole;

    private List<String> currentSkills;

    private Integer skillMatchPercentage;

    private Integer readinessScore;

    private List<String> missingSkills;

    private List<String> prioritySkills;

    private List<String> coreSkills;

    private List<String> advancedSkills;

    private List<String> foundationSkills;

    private List<String> recommendedProjects;

    private List<String> recommendedCertifications;

    private List<String> recommendedResources;

    private String careerPosition;

private String currentStage;

private String timeToJobReady;

private List<LearningStep> learningOrder;

private List<String> topInsights;

private List<String> focusNow;

private List<String> employerExpectations;

private List<String> commonMistakes;

private List<String> industryAdvice;

    private Boolean resumeConsidered;

    /* ===================================================== */

    // Used for cache validation

    private String profileHash;

    private LocalDateTime generatedAt;

}