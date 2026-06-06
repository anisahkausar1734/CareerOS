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
public class CareerAnalysisResponse {

    private Integer overallScore;

    private String targetDomain;

    private String dreamCompany;

    private ScoreBreakdown scoreBreakdown;

    private List<LostPoint> lostPoints;

    private List<String> recommendedSkills;

    private List<String> recommendedProjects;

    private String nextAction;
}