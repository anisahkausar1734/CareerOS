package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.CareerAnalysisResponse;
import com.careeros.careeros_backend.dto.CareerProgressResponse;
import com.careeros.careeros_backend.dto.InternshipReadinessResponse;
import com.careeros.careeros_backend.dto.JobReadinessResponse;
import com.careeros.careeros_backend.dto.ResumeAnalysisResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CareerAnalysisService {

    private final JobReadinessService
            jobReadinessService;

    private final InternshipReadinessService
            internshipReadinessService;

    private final ResumeAnalysisService
            resumeAnalysisService;

    private final CareerProgressService
            careerProgressService;

    public CareerAnalysisResponse analyzeCareer(
            String email
    ) {

        JobReadinessResponse job =
                jobReadinessService
                        .getReadiness(email);

        InternshipReadinessResponse internship =
                internshipReadinessService
                        .getReadiness(email);

        ResumeAnalysisResponse resume =
               resumeAnalysisService
        .getCachedAnalysis(email);
        
        CareerProgressResponse progress =
                careerProgressService
                        .getProgress(email);

        int jobContribution =

                (int)
                        (
                                job.getReadinessScore()
                                        * 0.30
                        );

        int internshipContribution =

                (int)
                        (
                                internship.getReadinessScore()
                                        * 0.25
                        );

        int resumeContribution =

                (int)
                        (
                                resume.getResumeScore()
                                        * 0.20
                        );

        // Temporary until interview integration

        int interviewContribution =

                (int)
                        (
                                80 * 0.15
                        );

        int learningContribution =

                (int)
                        (
                                progress.getExecutionProgress()
                                        * 0.10
                        );

        int careerReadinessScore =

                jobContribution

                        +

                        internshipContribution

                        +

                        resumeContribution

                        +

                        interviewContribution

                        +

                        learningContribution;

        return CareerAnalysisResponse
                .builder()

                .careerReadinessScore(
                        careerReadinessScore
                )

                .jobContribution(
                        jobContribution
                )

                .internshipContribution(
                        internshipContribution
                )

                .resumeContribution(
                        resumeContribution
                )

                .interviewContribution(
                        interviewContribution
                )

                .learningContribution(
                        learningContribution
                )

                .status(
                        careerReadinessScore >= 80
                                ? "Career Ready"

                                : careerReadinessScore >= 60
                                ? "On Track"

                                : "Needs Improvement"
                )

                .build();
    }
}