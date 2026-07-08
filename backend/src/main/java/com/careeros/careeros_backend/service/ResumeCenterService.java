package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.*;

import com.careeros.careeros_backend.model.StudentProfile;
import com.careeros.careeros_backend.repository.StudentProfileRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeCenterService {

    private final ResumeGapService
        resumeGapService;    

    private final StudentProfileRepository
            studentProfileRepository;

    private final ResumeAnalysisService
            resumeAnalysisService;

   
    public ResumeCenterResponse getResumeCenter(
            String email
    ) {

        StudentProfile profile =
                studentProfileRepository
                        .findByEmail(email)
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "Profile not found"
                                        )
                        );

       ResumeAnalysisResponse resume =
        resumeAnalysisService
                .getCachedAnalysis(email);
Integer atsScore =
        resume.getAtsScore();
        
        ResumeGapResponse gap =
        resumeGapService
                .analyzeGap(email);

                        System.out.println(
        "RESUME SCORE = "
        + resume.getResumeScore()
);


      

        List<String> recommendations =
        gap.getRecommendations();

        return ResumeCenterResponse
                .builder()
                .dreamRole(
                        profile.getDreamRole()
                )
                .careerReadiness(
                        profile.getCareerReadiness()
                )
                .resumeScore(
                        resume.getResumeScore()
                )

                
                .atsScore(
        atsScore
)
                .missingSkills(
        gap.getMissingSkills()
)
                .missingProjects(
        gap.getMissingProjects()
)
               
                .recommendations(
                        recommendations
                )
                .roleAlignmentScore(
        resume.getRoleAlignmentScore()
)

.skillsCoverageScore(
        resume.getSkillsCoverageScore()
)

.projectStrengthScore(
        resume.getProjectStrengthScore()
)

.internshipReadiness(
        resume.getInternshipReadiness()
)

.jobReadiness(
        resume.getJobReadiness()
)

.missingCertifications(
        gap.getMissingCertifications()
)


                .build();
    }
}