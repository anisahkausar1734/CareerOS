package com.careeros.careeros_backend.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ATSAnalysisResponse {

    private Integer atsScore;

    private Integer keywordScore;

    private Integer formattingScore;

    private Integer sectionScore;

    private Integer readabilityScore;

    private List<String> missingKeywords;

    private List<String> strengths;

    private List<String> improvements;

    private String verdict;

    // ATS Intelligence

private Integer parsingScore;

private Integer contactScore;

private Integer skillsMatchScore;

private Integer projectScore;

private Integer achievementScore;

// ATS Details

private List<String> detectedKeywords;

private List<String> missingSections;

private List<String> parsingIssues;

private List<String> atsRisks;

private List<String> quickWins;

private List<String> actionVerbsFound;

private List<String> weakActionVerbs;

private List<String> quantifiedAchievements;

private List<ATSSectionAnalysis> sectionAnalysis;

// Overall Analysis

private String resumeType;

private String atsSummary;


}