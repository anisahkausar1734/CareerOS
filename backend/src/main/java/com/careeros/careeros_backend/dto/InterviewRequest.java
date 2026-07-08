package com.careeros.careeros_backend.dto;

import lombok.Data;

@Data
public class InterviewRequest {

    private String company;

    private String role;

    private String resume;

    private String interviewType;

    private String customPrompt;

}