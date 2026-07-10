package com.careeros.careeros_backend.service.projectanalysis.career;

import com.careeros.careeros_backend.dto.ProfileResponse;
import com.careeros.careeros_backend.dto.projectanalysis.AnalysisPurpose;
import com.careeros.careeros_backend.dto.projectanalysis.ProjectCareerImpact;
import com.careeros.careeros_backend.dto.projectanalysis.ProjectEngineeringAnalysis;
import com.careeros.careeros_backend.service.projectanalysis.career.scoring.CareerScoreCalculator;
import com.careeros.careeros_backend.service.projectanalysis.career.scoring.HiringSignalCalculator;
import com.careeros.careeros_backend.service.projectanalysis.career.scoring.IndustryDemandCalculator;
import com.careeros.careeros_backend.service.projectanalysis.career.scoring.RoleAlignmentCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CareerImpactServiceImpl
        implements CareerImpactService {

    private final CareerScoreCalculator
            careerScoreCalculator;

    private final RoleAlignmentCalculator
            roleAlignmentCalculator;

    private final HiringSignalCalculator
            hiringSignalCalculator;

    private final IndustryDemandCalculator
            industryDemandCalculator;

    @Override
    public ProjectCareerImpact evaluate(

            ProjectEngineeringAnalysis engineering,

            ProfileResponse profile,

            AnalysisPurpose purpose

    ) {

        Integer overallCareerScore =
                careerScoreCalculator.calculate(
                        engineering,
                        profile
                );

        Integer roleAlignment =
                roleAlignmentCalculator.calculate(
                        engineering,
                        profile
                );

        Integer hiringSignal =
                hiringSignalCalculator.calculate(
                        engineering
                );

        Integer industryDemand =
                industryDemandCalculator.calculate(
                        engineering
                );

        return ProjectCareerImpact

                .builder()

                .analysisPurpose(
                        purpose
                )

                .overallCareerScore(
                        overallCareerScore
                )

                .resumeImpact(
                        overallCareerScore
                )

                .internshipImpact(
                        overallCareerScore
                )

                .jobImpact(
                        overallCareerScore
                )

                .researchImpact(
                        overallCareerScore
                )

                .startupImpact(
                        overallCareerScore
                )

                .openSourceImpact(
                        overallCareerScore
                )

                .roleAlignment(
                        roleAlignment
                )

                .industryDemand(
                        industryDemand
                )

                .hiringSignal(
                        hiringSignal
                )

                .confidence(
                        engineering.getConfidence()
                )

                .overallCareerVerdict(
                        generateVerdict(
                                overallCareerScore
                        )
                )

                .build();

    }

    private String generateVerdict(
            Integer score
    ) {

        if (score >= 90)
            return "Outstanding career impact.";

        if (score >= 80)
            return "Excellent career value.";

        if (score >= 70)
            return "Strong portfolio project.";

        if (score >= 60)
            return "Good learning project.";

        return "Needs further engineering improvements.";

    }

}