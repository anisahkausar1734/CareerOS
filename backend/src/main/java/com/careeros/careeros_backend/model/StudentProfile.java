package com.careeros.careeros_backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "student_profiles")
public class StudentProfile {

    @Id
    private String id;

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

private List<String> latestMissingSkills;

private List<String> latestPrioritySkills;


}