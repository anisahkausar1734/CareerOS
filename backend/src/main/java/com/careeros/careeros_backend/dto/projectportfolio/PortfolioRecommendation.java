package com.careeros.careeros_backend.dto.projectportfolio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioRecommendation {

    /*
     * Best Project
     */
    private String strongestProject;

    /*
     * Engineering Insights
     */
    private String strongestArea;

    private String weakestArea;

    /*
     * Recommendations
     */
    private String recommendedNextProject;

    private String overallRecommendation;

    /*
     * Technology Intelligence
     */
    private List<String> strongestTechnologies;

    private List<String> missingTechnologies;

}