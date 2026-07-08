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
public class JobMatchResponse {

    private Integer matchScore;

    private String explanation;

    private List<String> strengths;

    private List<String> missingSkills;

    private List<String> recommendations;
}