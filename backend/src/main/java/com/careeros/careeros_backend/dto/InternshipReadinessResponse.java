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
public class InternshipReadinessResponse {

    private Integer readinessScore;

    private String status;

    private List<String> strengths;

    private List<String> weaknesses;
}