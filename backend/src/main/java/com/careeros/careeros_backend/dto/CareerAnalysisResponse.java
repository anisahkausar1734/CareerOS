package com.careeros.careeros_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CareerAnalysisResponse {

    private Integer careerReadinessScore;

    private Integer jobContribution;

    private Integer internshipContribution;

    private Integer resumeContribution;

    private Integer interviewContribution;

    private Integer learningContribution;

    private String status;
}