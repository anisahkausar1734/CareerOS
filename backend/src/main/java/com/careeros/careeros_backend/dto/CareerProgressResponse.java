package com.careeros.careeros_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CareerProgressResponse {

    private Integer careerReadiness;

    private Integer resumeScore;

    private Integer atsScore;

    private Integer internshipReadiness;

    private Integer jobReadiness;

    private Integer executionProgress;

    private Integer completedTasks;

    private Integer totalTasks;

    private String status;

    private Integer resourcesCompleted;
}
