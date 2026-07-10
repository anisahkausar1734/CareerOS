package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.JobReadinessResponse;
import com.careeros.careeros_backend.dto.ResumeAnalysisResponse;
import com.careeros.careeros_backend.model.Project;
import com.careeros.careeros_backend.model.StudentProfile;
import com.careeros.careeros_backend.repository.ProjectRepository;
import com.careeros.careeros_backend.repository.StudentProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobReadinessService {

    private final StudentProfileRepository
            studentProfileRepository;

    private final ProjectRepository
            projectRepository;

    private final ResumeAnalysisService
            resumeAnalysisService;

    public JobReadinessResponse
    getReadiness(
            String email
    ) {

        StudentProfile profile =
                studentProfileRepository
                        .findByEmail(email)
                        .orElseThrow();

        ResumeAnalysisResponse resume =
                resumeAnalysisService
                .getCachedAnalysis(email);


        int skillsContribution =

                (int)
                        (
                                resume.getSkillsCoverageScore()
                                        * 0.25
                        );


        int resumeContribution =

                (int)
                        (
                                resume.getResumeScore()
                                        * 0.20
                        );

        // Temporary until interview module integration

        int interviewContribution = 10;

        // Temporary until experience tracking

        int experienceContribution = 8;

        // Temporary until certifications module

        int certificationContribution = 5;

        int readinessScore =

                skillsContribution

                        +


                        +

                        resumeContribution

                        +

                        interviewContribution

                        +

                        experienceContribution

                        +

                        certificationContribution;

        return JobReadinessResponse
                .builder()

                .readinessScore(
                        readinessScore
                )

                .skillsContribution(
                        skillsContribution
                )



                .resumeContribution(
                        resumeContribution
                )

                .interviewContribution(
                        interviewContribution
                )

                .experienceContribution(
                        experienceContribution
                )

                .certificationContribution(
                        certificationContribution
                )

                .status(
                        readinessScore >= 80
                                ? "Job Ready"

                                : readinessScore >= 60
                                ? "Moderately Ready"

                                : "Needs Improvement"
                )

                .build();
    }
}