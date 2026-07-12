package com.careeros.careeros_backend.dto.projectportfolio;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PortfolioCareerMetrics {

    private Integer internshipReadiness;

    private Integer resumeStrength;

    private Integer hiringSignal;

    private Integer roleAlignment;

}