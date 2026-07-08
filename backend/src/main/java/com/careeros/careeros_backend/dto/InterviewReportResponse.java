package com.careeros.careeros_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InterviewReportResponse {

    private double overallScore;

    private double technicalScore;

    private double communicationScore;

    private double problemSolvingScore;

    private double confidenceScore;

    private String strengths;

    private String improvements;

    private String finalRecommendation;

}