package com.careeros.careeros_backend.dto;

import lombok.Data;

@Data
public class ResumeRefinementRequestDTO {

    private String email;

    private String companyName;

    private String jobDescription;

    private String customPrompt;

}