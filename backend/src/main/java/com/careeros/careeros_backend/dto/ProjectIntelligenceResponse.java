package com.careeros.careeros_backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectIntelligenceResponse {

    private Integer overallProjectScore;

    private Integer averageEngineeringQuality;

    private Integer averageRoleAlignment;

    private Integer averageProductionReadiness;

    private Integer averageResumeImpact;

    private Integer averageInternshipImpact;

    private Integer averageJobImpact;

    private Integer projectCount;

    private String bestProject;
}