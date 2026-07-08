package com.careeros.careeros_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobReadinessResponse {

    private Integer readinessScore;

    private Integer skillsContribution;

    private Integer projectContribution;

    private Integer resumeContribution;

    private Integer interviewContribution;

    private Integer experienceContribution;

    private Integer certificationContribution;

    private String status;
}