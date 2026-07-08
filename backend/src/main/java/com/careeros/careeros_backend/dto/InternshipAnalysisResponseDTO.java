package com.careeros.careeros_backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class InternshipAnalysisResponseDTO {

    private Integer internshipScore;

    private Integer hiringProbability;

    private String verdict;

    private Integer roleAlignment;

    private Integer projectStrength;

    private Integer skillReadiness;

    private List<String> missingSkills;

    private List<String> missingProjects;

    private List<String> missingCertifications;

    private List<String> missingTools;

    private List<String> companyExpectations;

    private List<String> recommendations;

    private List<String> strengths;


    

}