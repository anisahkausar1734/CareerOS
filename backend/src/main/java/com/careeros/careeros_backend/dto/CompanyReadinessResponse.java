package com.careeros.careeros_backend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyReadinessResponse {

    private String company;

    private Integer readinessPercentage;

    private String feedback;

    private List<String> strengths;

    private List<String> missingSkills;
}