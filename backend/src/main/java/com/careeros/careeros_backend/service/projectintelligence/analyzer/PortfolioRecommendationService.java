package com.careeros.careeros_backend.service.projectintelligence.analyzer;
import com.careeros.careeros_backend.dto.projectportfolio.PortfolioRecommendation;

import com.careeros.careeros_backend.model.Project;

import java.util.List;

public interface PortfolioRecommendationService {

    PortfolioRecommendation analyze(
            List<Project> projects
    );

}