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
public class DashboardResponseDTO {

    // ================= HERO =================

    private String fullName;

    private String dreamRole;

    private String heroTitle;

    private String heroDescription;

    private String ctaLabel;

    private String ctaRoute;

    // ================= SNAPSHOT =================

    private Integer resumeScore;

    private Integer atsScore;

    private Integer careerReadiness;

    private Integer internshipReadiness;

    private Integer projectScore;

    private Integer roleAlignment;

    private Integer roadmapProgress;

    private String topStrength;

    // ================= JOURNEY =================

    private String currentStage;

    private String nextStage;

    private Integer completedStages;

    private Integer totalStages;

    // ================= QUICK STATS =================

    private Integer completedProjects;

    private Integer applications;

    private Integer interviewsTaken;

    private Integer certifications;

    // ================= DASHBOARD =================

    private List<RecommendedActionDTO> recommendedActions;

    private List<FeatureStatusDTO> featureStatus;

}