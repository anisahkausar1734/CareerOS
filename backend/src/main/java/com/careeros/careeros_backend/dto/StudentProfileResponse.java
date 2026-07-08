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
public class StudentProfileResponse {

    private String email;

    private String fullName;

    private String phoneNumber;

    private String collegeName;

    private String degree;

    private String branch;

    private String currentYear;

    private Integer graduationYear;

    private String dreamRole;

    private List<String> skills;

    private Integer careerReadiness;

    private String currentStage;

    private Boolean skillGapCompleted;

private Boolean roadmapCompleted;

private Boolean resumeAnalysisCompleted;

private Boolean interviewCompleted;

private Boolean applicationsStarted;

private Boolean hasResume;
}