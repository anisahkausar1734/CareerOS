package com.careeros.careeros_backend.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class InternshipMatchResponseDTO {

    private Integer matchScore;

    private Integer hiringProbability;

    private List<String> strengths;

    private List<String> missingSkills;

    private List<String> recommendations;

    private String verdict;
}