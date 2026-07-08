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
@Document(collection = "ats_analysis")
public class ATSAnalysis {

    @Id
    private String id;

    private String email;

    private Integer atsScore;
    private Integer parsingScore;
    private Integer keywordScore;
    private Integer contactScore;
    private Integer sectionScore;
    private Integer formattingScore;
    private Integer readabilityScore;
    private Integer skillsMatchScore;
    private Integer projectScore;
    private Integer achievementScore;

    private List<String> detectedKeywords;
    private List<String> missingKeywords;
    private List<String> missingSections;
    private List<String> parsingIssues;
    private List<String> atsRisks;
    private List<String> quickWins;
    private List<String> actionVerbsFound;
    private List<String> weakActionVerbs;
    private List<String> quantifiedAchievements;

    private List<String> strengths;
    private List<String> improvements;

    private String resumeType;
    private String atsSummary;
    private String verdict;

    private String resumeHash;

    private LocalDateTime analyzedAt;
}