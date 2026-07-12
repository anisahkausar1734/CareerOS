package com.careeros.careeros_backend.service.projectintelligence.analyzer;

import com.careeros.careeros_backend.dto.projectportfolio.PortfolioCareerMetrics;
import com.careeros.careeros_backend.model.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CareerPortfolioAnalyzerImpl
        implements CareerPortfolioAnalyzer {

    @Override
    public PortfolioCareerMetrics analyze(
            List<Project> projects
    ) {

        if (projects == null || projects.isEmpty()) {

            return PortfolioCareerMetrics.builder()
                    .internshipReadiness(0)
                    .resumeStrength(0)
                    .hiringSignal(0)
                    .roleAlignment(0)
                    .build();

        }

        int internship = 0;
        int resume = 0;
        int hiring = 0;
        int role = 0;

        int analyzed = 0;

        for (Project project : projects) {

            if (project.getCareerImpact() == null) {
                continue;
            }

            analyzed++;

            internship += project.getCareerImpact().getInternshipImpact();
            resume += project.getCareerImpact().getResumeImpact();
            hiring += project.getCareerImpact().getHiringSignal();
            role += project.getCareerImpact().getRoleAlignment();

        }

        if (analyzed == 0) {
            analyzed = 1;
        }

        return PortfolioCareerMetrics.builder()

                .internshipReadiness(
                        internship / analyzed
                )

                .resumeStrength(
                        resume / analyzed
                )

                .hiringSignal(
                        hiring / analyzed
                )

                .roleAlignment(
                        role / analyzed
                )

                .build();

    }

}