package com.careeros.careeros_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeGapResponse {

    private Integer matchPercentage;

    private List<String> missingSkills;

    private List<String> missingProjects;

    private List<String> missingCertifications;

    private List<String> recommendations;

}