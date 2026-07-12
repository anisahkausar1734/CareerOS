package com.careeros.careeros_backend.dto.projectportfolio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioEngineeringMetrics {

    /*
     * Overall
     */
    private Integer portfolioScore;

    private Integer averageEngineeringScore;

    /*
     * Architecture
     */
    private Integer averageArchitectureScore;

    /*
     * Codebase
     */
    private Integer averageCodeQuality;

    private Integer averageMaintainability;

    private Integer averageRepositoryOrganization;

    /*
     * Engineering Practices
     */
    private Integer averageTestingQuality;

    private Integer averageSecurityScore;

    private Integer averageDocumentationQuality;

    private Integer averageBuildQuality;

    private Integer averageDependencyManagement;

    private Integer averageDeploymentReadiness;

    private Integer averageProductionReadiness;

    /*
     * Engineering Excellence
     */
    private Integer averageInnovationScore;

    private Integer averageTechnicalComplexity;

    private Integer averageScalability;

    /*
     * Repository Confidence
     */
    private Integer averageEvidenceCoverage;

    /*
     * Engineering Maturity
     */
    private Integer averageMaturityScore;

}