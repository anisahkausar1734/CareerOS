package com.careeros.careeros_backend.dto;

import lombok.Data;

@Data
public class InternshipAnalysisRequestDTO {

    private String email;

    private String targetRole;

    private String targetCompany;

    private String customRequirements;

}