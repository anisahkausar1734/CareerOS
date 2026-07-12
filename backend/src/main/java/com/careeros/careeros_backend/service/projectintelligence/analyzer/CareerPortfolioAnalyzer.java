package com.careeros.careeros_backend.service.projectintelligence.analyzer;
import com.careeros.careeros_backend.dto.projectportfolio.PortfolioCareerMetrics;

import com.careeros.careeros_backend.model.Project;

import java.util.List;

public interface CareerPortfolioAnalyzer {

    PortfolioCareerMetrics analyze(
            List<Project> projects
    );

}