package com.careeros.careeros_backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "interview_sessions")
public class InterviewSession {

    @Id
    private String id;

    private String company;

    private String role;

    private String resume;

    private String interviewType;

    private String customPrompt;

    private String email;

    private boolean active = true;

    private int questionCount = 0;

    private double totalScore = 0;

    private LocalDateTime createdAt =
            LocalDateTime.now();

    private LocalDateTime endedAt;        

    private List<String> conversationHistory =
            new ArrayList<>();

    private List<InterviewEvaluation> evaluations =
        new ArrayList<>();     
        
    private Double finalScore;

private String finalRecommendation;

private String finalStrengths;

private String finalImprovements;

private Integer totalQuestionsAsked;

}