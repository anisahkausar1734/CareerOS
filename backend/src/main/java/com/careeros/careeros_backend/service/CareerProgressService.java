package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.CareerProgressResponse;
import com.careeros.careeros_backend.dto.InternshipReadinessResponse;
import com.careeros.careeros_backend.dto.JobReadinessResponse;
import com.careeros.careeros_backend.dto.ResumeAnalysisResponse;
import com.careeros.careeros_backend.model.StudentProfile;
import com.careeros.careeros_backend.repository.StudentProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.careeros.careeros_backend.dto.ProjectIntelligenceResponse;


@Service
@RequiredArgsConstructor
public class CareerProgressService {


    private final ResumeAnalysisService
        resumeAnalysisService;

private final InternshipReadinessService
        internshipReadinessService;

private final JobReadinessService
        jobReadinessService;

private final StudentProfileRepository
        studentProfileRepository;

private final ProjectService
        projectService;        

   public CareerProgressResponse getProgress(
        String email
) {

    StudentProfile profile =
            studentProfileRepository
                    .findByEmail(email)
                    .orElseThrow();

    ResumeAnalysisResponse resume =
            resumeAnalysisService
                .getCachedAnalysis(email);
                
    InternshipReadinessResponse internship =
            internshipReadinessService
                    .getReadiness(email);

    JobReadinessResponse job =
            jobReadinessService
                    .getReadiness(email);

                    ProjectIntelligenceResponse projectIntel =
        projectService
                .getProjectIntelligence(
                        email
                );

    int completedTasks = 0;

    if(Boolean.TRUE.equals(profile.getSkillGapCompleted()))
        completedTasks++;

    if(Boolean.TRUE.equals(profile.getRoadmapCompleted()))
        completedTasks++;

    if(Boolean.TRUE.equals(profile.getResumeAnalysisCompleted()))
        completedTasks++;

    if(Boolean.TRUE.equals(profile.getInterviewCompleted()))
        completedTasks++;

    if(Boolean.TRUE.equals(profile.getApplicationsStarted()))
        completedTasks++;

    int totalTasks = 5;

    int executionProgress =
            (completedTasks * 100)
                    / totalTasks;

    return CareerProgressResponse
            .builder()

            .careerReadiness(
        calculateCareerReadiness(
                resume,
                internship,
                job,
                projectIntel,
                executionProgress
        )
)

            .resumeScore(
                    resume.getResumeScore()
            )

            .atsScore(
                    resume.getAtsScore()
            )

            .internshipReadiness(
                    internship.getReadinessScore()
            )

            .jobReadiness(
                    job.getReadinessScore()
            )

            .executionProgress(
                    executionProgress
            )

            .completedTasks(
                    completedTasks
            )

            .totalTasks(
                    totalTasks
            )

            .status(
                    executionProgress >= 80
                            ? "Career Ready"

                            : executionProgress >= 50
                            ? "On Track"

                            : "Needs Improvement"
            )

            .build();
}

private Integer calculateCareerReadiness(

        ResumeAnalysisResponse resume,

        InternshipReadinessResponse internship,

        JobReadinessResponse job,

        ProjectIntelligenceResponse projectIntel,

        Integer executionProgress

)
{

    double score =

            (resume.getResumeScore() * 0.20)

            +

            (resume.getAtsScore() * 0.15)

            +

            (
                    projectIntel
                            .getOverallProjectScore()
                    * 0.25
            )

            +

            (
                    internship
                            .getReadinessScore()
                    * 0.15
            )

            +

            (
                    job
                            .getReadinessScore()
                    * 0.15
            )

            +

            (
                    executionProgress
                    * 0.10
            );

    return (int) Math.round(score);
}

}