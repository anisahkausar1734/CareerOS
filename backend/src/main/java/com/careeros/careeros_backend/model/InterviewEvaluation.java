package com.careeros.careeros_backend.model;

import lombok.Data;

@Data
public class InterviewEvaluation {

    private double technicalScore;

    private double communicationScore;

    private double problemSolvingScore;

    private double confidenceScore;

    private String strengths;

    private String improvements;

}