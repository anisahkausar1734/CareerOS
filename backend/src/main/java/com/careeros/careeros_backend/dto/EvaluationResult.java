package com.careeros.careeros_backend.dto;

import lombok.Data;

@Data
public class EvaluationResult {

    private double technicalScore;

    private double communicationScore;

    private double problemSolvingScore;

    private double confidenceScore;

    private String strengths;

    private String improvements;

}