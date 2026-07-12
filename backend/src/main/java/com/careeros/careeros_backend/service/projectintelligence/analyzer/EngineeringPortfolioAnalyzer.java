package com.careeros.careeros_backend.service.projectintelligence.analyzer;

import com.careeros.careeros_backend.dto.projectportfolio.PortfolioEngineeringMetrics;
import com.careeros.careeros_backend.model.Project;

import java.util.List;

public interface EngineeringPortfolioAnalyzer {

    PortfolioEngineeringMetrics analyze(
            List<Project> projects
    );

}