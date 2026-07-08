package com.careeros.careeros_backend.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeCenterResponse {

    private Integer resumeScore;

    private Integer atsScore;

    private String dreamRole;

    private Integer careerReadiness;

    private Integer roleAlignmentScore;

private Integer skillsCoverageScore;

private Integer projectStrengthScore;

private Integer internshipReadiness;

private Integer jobReadiness;

private String summary;

private String verdict;

private List<String> missingCertifications;

    private List<String> missingSkills;

    private List<String> missingProjects;

    private List<String> recommendations;

    private List<String> strengths;

    private List<String> weaknesses;

}