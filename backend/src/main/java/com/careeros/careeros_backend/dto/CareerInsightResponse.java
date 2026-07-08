package com.careeros.careeros_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CareerInsightResponse {

    private String strongestArea;

    private String weakestArea;

    private String keyInsight;

    private String biggestRisk;

    private String biggestOpportunity;

    private String recommendedFocus;

    private String expectedOutcome;

    private String careerSummary;

}