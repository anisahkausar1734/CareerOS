package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.ProjectIntelligenceResponse;
import com.careeros.careeros_backend.dto.ResumeAnalysisResponse;
import com.careeros.careeros_backend.dto.SkillGapResponse;
import com.careeros.careeros_backend.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.careeros.careeros_backend.model.StudentProfile;
import com.careeros.careeros_backend.repository.StudentProfileRepository;
import com.careeros.careeros_backend.model.Project;
import com.careeros.careeros_backend.model.Resume;
import com.careeros.careeros_backend.model.ResumeAnalysis;
import com.careeros.careeros_backend.repository.ProjectRepository;
import com.careeros.careeros_backend.repository.ResumeAnalysisRepository;
import com.careeros.careeros_backend.service.ProjectService;

        import java.util.Comparator;
        import java.util.stream.Collectors;
        import java.util.List;

        @Service
        @RequiredArgsConstructor
        public class ResumeAnalysisService {
        private final ResumeRepository
                resumeRepository;

        private final GeminiService
                geminiService;

        private final StudentProfileRepository
                studentProfileRepository;

        private final ProjectService
                projectService;        

        private final ResumeAnalysisRepository
                resumeAnalysisRepository;

        private final SkillGapService
                skillGapService;



        public ResumeAnalysisResponse
        getCachedAnalysis(
                String email
                
        ) {

        
        StudentProfile profile =
                studentProfileRepository
                        .findByEmail(email)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Profile not found"
                                )
                        );

        return resumeAnalysisRepository
                .findByEmail(email)
                .map(
                        analysis ->
                                mapToResponse(
                                        analysis,
                                        profile
                                )
                )
                .orElse(
                        ResumeAnalysisResponse
                                .builder()
                                .executiveSummary(
                                        "No analysis found."
                                )
                                .build()
                );

        }



public ResumeAnalysisResponse
reanalyzeResume(
        String email
) {

    resumeAnalysisRepository
            .findByEmail(email)
            .ifPresent(
                    resumeAnalysisRepository::delete
            );

    return analyzeResume(email);

}



public ResumeAnalysisResponse
analyzeResume(
        String email
) {
StudentProfile profile =
        studentProfileRepository
                .findByEmail(email)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Profile not found"
                        )
                );

var existingAnalysis =
        resumeAnalysisRepository
                .findByEmail(email);

if (existingAnalysis.isPresent()) {

    System.out.println(
            "Returning Saved Resume Analysis"
    );

    return mapToResponse(
            existingAnalysis.get(),
            profile
    );

}

Resume resume =
        resumeRepository
                .findByEmail(email)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Resume not found"
                        )
                );
   
    SkillGapResponse skillGap =
            skillGapService
                    .getSkillGap(email);
ProjectIntelligenceResponse projectIntel =
        projectService
                .getProjectIntelligence(
                        email
                );


    String dreamRole =
            profile.getDreamRole();

    String currentYear =
            profile.getCurrentYear();

    Integer graduationYear =
            profile.getGraduationYear();

    Integer careerReadiness =
            profile.getCareerReadiness();

    String degree =
            profile.getDegree();

    String branch =
            profile.getBranch();

    String profileStage =
            profile.getCurrentStage();

    List<String> skills =
            profile.getSkills();

    String resumeText =
            resume.getResumeText();

    List<String> prioritySkills =
            skillGap.getPrioritySkills();

    List<String> missingSkills =
            skillGap.getMissingSkills();

    String careerPosition =
            skillGap.getCareerPosition();

    String skillGapStage =
            skillGap.getCurrentStage();

  String prompt = """
You are CareerOS Resume Intelligence.

You are an experienced:

- Senior Software Engineering Recruiter
- Engineering Manager
- Technical Interviewer
- Career Mentor
- Resume Reviewer

Your responsibility is NOT to rewrite the resume.

Your responsibility is to understand what this resume communicates about the student.

Analyze the resume using ALL available information.

==================================================
STUDENT PROFILE
==================================================

Dream Role:
%s

Current Academic Year:
%s

Graduation Year:
%s

Degree:
%s

Branch:
%s

Current Career Stage:
%s

Career Readiness:
%s

Technical Skills:
%s

==================================================
SKILL GAP CONTEXT
==================================================

Career Position:
%s

Current Skill Stage:
%s

Priority Skills:
%s

Missing Skills:
%s

==================================================
PROJECT INTELLIGENCE
==================================================

Project Count:
%s

Best Project:
%s

Overall Project Score:
%.0f

Resume Impact:
%d

Internship Impact:
%d

Job Impact:
%d

Average Engineering Quality:
%d

Average Production Readiness:
%d

Average Role Alignment:
%d

==================================================
RESUME CONTEXT
==================================================

Has Resume:
Yes

Analysis Version:
v2.0
==================================================
RESUME
==================================================

%s

==================================================
YOUR TASK
==================================================

Understand what this resume communicates about the student.

Treat this as a professional recruiter review.

The goal is not to judge the student.

The goal is to determine whether the resume accurately represents the student's current capabilities, academic stage, technical readiness and career potential.

Every observation must be personalized.

Do not generate generic resume advice.

Evaluate whether the resume accurately represents:

• Current academic stage

• Dream role

• Technical ability

• Project quality

• Professional maturity

• Career readiness

• Overall presentation

Do NOT explain how to improve.

Do NOT recommend courses.

Do NOT recommend certifications.

Do NOT recommend projects.

Do NOT provide implementation steps.

Do NOT provide learning resources.

Only provide professional observations and insights.

==================================================
RETURN ONLY
==================================================

EXECUTIVE_SUMMARY=text

OVERALL_VERDICT=text

CONFIDENCE_LEVEL=text

CURRENT_RESUME_IDENTITY=text

DESIRED_IDENTITY=text

IDENTITY_ALIGNMENT=number

ATS_SCORE=number

ROLE_ALIGNMENT=number

SKILLS_COVERAGE=number

ROLE_ALIGNMENT:
Evaluate how well the resume aligns with the student's dream role.

SKILLS_COVERAGE:
Evaluate how well the listed technical skills support the dream role and current academic stage.

IDENTITY_GAP=text

CURRENT_ACADEMIC_STAGE=text

EXPECTED_RESUME_LEVEL=text

CURRENT_RESUME_LEVEL=text

STAGE_EVALUATION=text

TOP_STRENGTHS=item1|item2|item3|item4|item5

RESUME_GAPS=item1|item2|item3|item4|item5

KEY_INSIGHTS=item1|item2|item3|item4|item5

HIDDEN_POTENTIAL=text

RESUME_NARRATIVE=text

RECRUITER_FIRST_IMPRESSION=text

RECRUITER_OPINION=text

RECRUITER_CONFIDENCE=text

GROWTH_POTENTIAL=text

POTENTIAL_RESUME_SCORE=number

CAREER_PROJECTION=text

MISSING_RESUME_COMPONENTS=item1|item2|item3|item4|item5

NEXT_MILESTONE=text

GUIDE_PREVIEW=text

RESUME_CONFIDENCE=text

ONE_LINE_RESUME_VERDICT=text

RESUME_MATURITY=text

BIGGEST_COMPETITIVE_ADVANTAGE=text

BIGGEST_COMPETITIVE_WEAKNESS=text

==================================================
RULES
==================================================

Return ONLY the fields above.

Do not return markdown.

Do not use headings.

Do not number anything.

Use "|" as the separator for every list.

Never use commas for list values.

Keep every paragraph under 60 words.

Keep every observation personalized to THIS student.

Do not repeat the same insight twice.

Base every observation on the student's current academic stage and dream role.

If the student is in an early academic stage (1st or 2nd year), evaluate relative to realistic expectations for that stage.

Do not penalize missing internships or advanced experience that would not normally be expected.

If there are no projects or project intelligence is limited, focus your evaluation on the resume and academic profile instead of heavily penalizing the student.

Never compare the student with senior professionals.

Always evaluate relative to the student's current academic stage.

Never criticize without explaining what the observation means.

Do not assume experience that is not present in the resume.

Avoid generic statements.

ATS_SCORE should evaluate formatting, readability, section completeness and ATS compatibility.

ROLE_ALIGNMENT should evaluate alignment with the dream role.

SKILLS_COVERAGE should evaluate how well the listed skills support the dream role and academic stage.

""".formatted(

        dreamRole,

        currentYear,

        graduationYear,

        degree,

        branch,

        profileStage,

        careerReadiness,

        skills,

        careerPosition,

        skillGapStage,

        prioritySkills,

        missingSkills,

        resumeText

);

    String response;

try {
System.out.println(
        prompt
);
    response =
            geminiService
                    .askGeminiCustom(
                            prompt
                    );

} catch (Exception e) {

    System.out.println(
            "Gemini Error: "
            + e.getMessage()
    );

    var savedAnalysis =
            resumeAnalysisRepository
                    .findByEmail(email);

    if(savedAnalysis.isPresent()) {

        System.out.println(
                "Returning Cached Analysis"
        );
return mapToResponse(
        savedAnalysis.get(),
        profile
);
    }

    return ResumeAnalysisResponse
            .builder()
            .build();
}

                    System.out.println(
        "GEMINI RESPONSE:\n"
        + response
);

  ResumeAnalysisResponse result =
        parseResumeAnalysisResponse(response);

int calculatedResumeScore =

(int)

(
        result.getAtsScore() * 0.25

        +

        result.getSkillsCoverageScore() * 0.25

       
        +

        85 * 0.10

        +

        result.getRoleAlignmentScore() * 0.10

        +

        80 * 0.05
);

result.setResumeScore(
        calculatedResumeScore
);

int internshipReadiness =

(int)

(
        result.getSkillsCoverageScore() * 0.30

        +


        calculatedResumeScore * 0.15

        +

        result.getRoleAlignmentScore() * 0.15

        +

        profile.getCareerReadiness() * 0.10
);



int jobReadiness =

(int)

(
        result.getSkillsCoverageScore() * 0.25

        +


        calculatedResumeScore * 0.20

        +

        result.getRoleAlignmentScore() * 0.15

        +

        profile.getCareerReadiness() * 0.15
);

result.setJobReadiness(
        jobReadiness
);

result.setInternshipReadiness(
        internshipReadiness
);
result.setDreamRole(
        dreamRole
);

result.setCurrentYear(
        currentYear
);

result.setCurrentAcademicStage(
        profileStage
);

result.setAnalysisVersion(
        "v2.0"
);

result.setReadyForResumeGuide(
        true
);


resumeAnalysisRepository.save(

       ResumeAnalysis.builder()

        .email(email)

        /* ==========================================
                    SCORES
           ========================================== */

        .resumeScore(
                result.getResumeScore()
        )

        .atsScore(
                result.getAtsScore()
        )

        .roleAlignmentScore(
                result.getRoleAlignmentScore()
        )

        .skillsCoverageScore(
                result.getSkillsCoverageScore()
        )

        .projectStrengthScore(
                result.getProjectStrengthScore()
        )

        .internshipReadiness(
                result.getInternshipReadiness()
        )

        .jobReadiness(
                result.getJobReadiness()
        )

        /* ==========================================
                EXECUTIVE INTELLIGENCE
           ========================================== */

        .executiveSummary(
                result.getExecutiveSummary()
        )

        .overallVerdict(
                result.getOverallVerdict()
        )

        .confidenceLevel(
                result.getConfidenceLevel()
        )

        /* ==========================================
                    RESUME IDENTITY
           ========================================== */

        .currentResumeIdentity(
                result.getCurrentResumeIdentity()
        )

        .desiredIdentity(
                result.getDesiredIdentity()
        )

        .identityAlignmentScore(
                result.getIdentityAlignmentScore()
        )

        .identityGap(
                result.getIdentityGap()
        )

        /* ==========================================
                  STAGE EVALUATION
           ========================================== */

        .expectedResumeLevel(
                result.getExpectedResumeLevel()
        )

        .currentResumeLevel(
                result.getCurrentResumeLevel()
        )

        .stageEvaluation(
                result.getStageEvaluation()
        )

        /* ==========================================
                    INSIGHTS
           ========================================== */

        .topStrengths(
                result.getTopStrengths()
        )

        .resumeGaps(
                result.getResumeGaps()
        )

        .keyInsights(
                result.getKeyInsights()
        )

        .hiddenPotential(
                result.getHiddenPotential()
        )

        .resumeNarrative(
                result.getResumeNarrative()
        )

        /* ==========================================
               RECRUITER PERSPECTIVE
           ========================================== */

        .recruiterFirstImpression(
                result.getRecruiterFirstImpression()
        )

        .recruiterOpinion(
                result.getRecruiterOpinion()
        )

        .recruiterConfidence(
                result.getRecruiterConfidence()
        )

        /* ==========================================
                RESUME POTENTIAL
           ========================================== */

        .growthPotential(
                result.getGrowthPotential()
        )

        .potentialResumeScore(
                result.getPotentialResumeScore()
        )

        .careerProjection(
                result.getCareerProjection()
        )

        /* ==========================================
                NEXT MILESTONE
           ========================================== */

        .nextMilestone(
                result.getNextMilestone()
        )

        .guidePreview(
                result.getGuidePreview()
        )

        .readyForResumeGuide(
                result.getReadyForResumeGuide()
        )

        /* ==========================================
                MISSING COMPONENTS
           ========================================== */

        .missingResumeComponents(
                result.getMissingResumeComponents()
        )

        /* ==========================================
                    EXTRA
           ========================================== */

        .resumeConfidence(
                result.getResumeConfidence()
        )

        .oneLineResumeVerdict(
                result.getOneLineResumeVerdict()
        )

        .analysisVersion(
                result.getAnalysisVersion()
        )

        .analyzedAt(
                java.time.LocalDateTime.now()
        )

        .build()
);


    return result;
}


private ResumeAnalysisResponse
parseResumeAnalysisResponse(
        String response
) {

    ResumeAnalysisResponse result =
            new ResumeAnalysisResponse();

try {

    String[] lines =
            response.split("\n");

    for (String line : lines) {

       
if (line.startsWith("EXECUTIVE_SUMMARY=")) {

    result.setExecutiveSummary(
            line.replace(
                    "EXECUTIVE_SUMMARY=",
                    ""
            )
    );

}

if (line.startsWith("OVERALL_VERDICT=")) {

    result.setOverallVerdict(
            line.replace(
                    "OVERALL_VERDICT=",
                    ""
            )
    );

}

if (line.startsWith("CONFIDENCE_LEVEL=")) {

    result.setConfidenceLevel(
            line.replace(
                    "CONFIDENCE_LEVEL=",
                    ""
            )
    );

}

if (line.startsWith("CURRENT_RESUME_IDENTITY=")) {

    result.setCurrentResumeIdentity(
            line.replace(
                    "CURRENT_RESUME_IDENTITY=",
                    ""
            )
    );

}

if (line.startsWith("CURRENT_ACADEMIC_STAGE=")) {

    result.setCurrentAcademicStage(
            line.replace(
                    "CURRENT_ACADEMIC_STAGE=",
                    ""
            )
    );

}

if (line.startsWith("EXPECTED_RESUME_LEVEL=")) {

    result.setExpectedResumeLevel(
            line.replace(
                    "EXPECTED_RESUME_LEVEL=",
                    ""
            )
    );

}

if (line.startsWith("CURRENT_RESUME_LEVEL=")) {

    result.setCurrentResumeLevel(
            line.replace(
                    "CURRENT_RESUME_LEVEL=",
                    ""
            )
    );

}

if (line.startsWith("STAGE_EVALUATION=")) {

    result.setStageEvaluation(
            line.replace(
                    "STAGE_EVALUATION=",
                    ""
            )
    );

}

if (line.startsWith("TOP_STRENGTHS=")) {

    result.setTopStrengths(
            List.of(
                    line.replace(
                            "TOP_STRENGTHS=",
                            ""
                    ).split("\\|")
            )
    );

}

if (line.startsWith("RESUME_GAPS=")) {

    result.setResumeGaps(
            List.of(
                    line.replace(
                            "RESUME_GAPS=",
                            ""
                    ).split("\\|")
            )
    );

}

if (line.startsWith("KEY_INSIGHTS=")) {

    result.setKeyInsights(
            List.of(
                    line.replace(
                            "KEY_INSIGHTS=",
                            ""
                    ).split("\\|")
            )
    );

}

if (line.startsWith("HIDDEN_POTENTIAL=")) {

    result.setHiddenPotential(
            line.replace(
                    "HIDDEN_POTENTIAL=",
                    ""
            )
    );

}

if (line.startsWith("RESUME_NARRATIVE=")) {

    result.setResumeNarrative(
            line.replace(
                    "RESUME_NARRATIVE=",
                    ""
            )
    );

}

if (line.startsWith("RECRUITER_FIRST_IMPRESSION=")) {

    result.setRecruiterFirstImpression(
            line.replace(
                    "RECRUITER_FIRST_IMPRESSION=",
                    ""
            )
    );

}

if (line.startsWith("RECRUITER_OPINION=")) {

    result.setRecruiterOpinion(
            line.replace(
                    "RECRUITER_OPINION=",
                    ""
            )
    );

}

if (line.startsWith("RECRUITER_CONFIDENCE=")) {

    result.setRecruiterConfidence(
            line.replace(
                    "RECRUITER_CONFIDENCE=",
                    ""
            )
    );

}

if (line.startsWith("GROWTH_POTENTIAL=")) {

    result.setGrowthPotential(
            line.replace(
                    "GROWTH_POTENTIAL=",
                    ""
            )
    );

}

if (line.startsWith("POTENTIAL_RESUME_SCORE=")) {

    result.setPotentialResumeScore(
            Integer.parseInt(
                    line.replace(
                            "POTENTIAL_RESUME_SCORE=",
                            ""
                    ).trim()
            )
    );

}

if (line.startsWith("CAREER_PROJECTION=")) {

    result.setCareerProjection(
            line.replace(
                    "CAREER_PROJECTION=",
                    ""
            )
    );

}

if (line.startsWith("MISSING_RESUME_COMPONENTS=")) {

    result.setMissingResumeComponents(
            List.of(
                    line.replace(
                            "MISSING_RESUME_COMPONENTS=",
                            ""
                    ).split("\\|")
            )
    );

}

if (line.startsWith("NEXT_MILESTONE=")) {

    result.setNextMilestone(
            line.replace(
                    "NEXT_MILESTONE=",
                    ""
            )
    );

}

if (line.startsWith("GUIDE_PREVIEW=")) {

    result.setGuidePreview(
            line.replace(
                    "GUIDE_PREVIEW=",
                    ""
            )
    );

}

if (line.startsWith("RESUME_CONFIDENCE=")) {

    result.setResumeConfidence(
            line.replace(
                    "RESUME_CONFIDENCE=",
                    ""
            )
    );

}

if (line.startsWith("ONE_LINE_RESUME_VERDICT=")) {

    result.setOneLineResumeVerdict(
            line.replace(
                    "ONE_LINE_RESUME_VERDICT=",
                    ""
            )
    );

}

if (line.startsWith("DESIRED_IDENTITY=")) {

    result.setDesiredIdentity(
            line.replace(
                    "DESIRED_IDENTITY=",
                    ""
            )
    );

}

if (line.startsWith("IDENTITY_ALIGNMENT=")) {

    result.setIdentityAlignmentScore(
            Integer.parseInt(
                    line.replace(
                            "IDENTITY_ALIGNMENT=",
                            ""
                    ).trim()
            )
    );

}

if (line.startsWith("ATS_SCORE=")) {

    result.setAtsScore(
            Integer.parseInt(
                    line.replace(
                            "ATS_SCORE=",
                            ""
                    ).trim()
            )
    );

}

if (line.startsWith("ROLE_ALIGNMENT=")) {

    result.setRoleAlignmentScore(
            Integer.parseInt(
                    line.replace(
                            "ROLE_ALIGNMENT=",
                            ""
                    ).trim()
            )
    );

}

if (line.startsWith("SKILLS_COVERAGE=")) {

    result.setSkillsCoverageScore(
            Integer.parseInt(
                    line.replace(
                            "SKILLS_COVERAGE=",
                            ""
                    ).trim()
            )
    );

}

if (line.startsWith("IDENTITY_GAP=")) {

    result.setIdentityGap(
            line.replace(
                    "IDENTITY_GAP=",
                    ""
            )
    );

}

        line = line.trim();

    }

   } catch (Exception e) {

    e.printStackTrace();

}

    return result;
}

private ResumeAnalysisResponse
mapToResponse(
        ResumeAnalysis analysis,
        StudentProfile profile
) {

   return ResumeAnalysisResponse.builder()

        /* ==========================================
                    SCORES
           ========================================== */

        .resumeScore(
                analysis.getResumeScore()
        )

        .atsScore(
                analysis.getAtsScore()
        )

        .roleAlignmentScore(
                analysis.getRoleAlignmentScore()
        )

        .skillsCoverageScore(
                analysis.getSkillsCoverageScore()
        )

        .projectStrengthScore(
                analysis.getProjectStrengthScore()
        )

        .internshipReadiness(
                analysis.getInternshipReadiness()
        )

        .jobReadiness(
                analysis.getJobReadiness()
        )
        .dreamRole(
        profile.getDreamRole()
)

.currentYear(
        profile.getCurrentYear()
)

.currentAcademicStage(
        profile.getCurrentStage()
)

        /* ==========================================
                EXECUTIVE INTELLIGENCE
           ========================================== */

        .executiveSummary(
                analysis.getExecutiveSummary()
        )

        .overallVerdict(
                analysis.getOverallVerdict()
        )

        .confidenceLevel(
                analysis.getConfidenceLevel()
        )

        /* ==========================================
                RESUME IDENTITY
           ========================================== */

        .currentResumeIdentity(
                analysis.getCurrentResumeIdentity()
        )

        .desiredIdentity(
                analysis.getDesiredIdentity()
        )

        .identityAlignmentScore(
                analysis.getIdentityAlignmentScore()
        )

        .identityGap(
                analysis.getIdentityGap()
        )

        /* ==========================================
                STAGE EVALUATION
           ========================================== */

        .currentResumeLevel(
                analysis.getCurrentResumeLevel()
        )

        .expectedResumeLevel(
                analysis.getExpectedResumeLevel()
        )

        .stageEvaluation(
                analysis.getStageEvaluation()
        )

        /* ==========================================
                    INSIGHTS
           ========================================== */

        .topStrengths(
                analysis.getTopStrengths()
        )

        .resumeGaps(
                analysis.getResumeGaps()
        )

        .keyInsights(
                analysis.getKeyInsights()
        )

        .hiddenPotential(
                analysis.getHiddenPotential()
        )

        .resumeNarrative(
                analysis.getResumeNarrative()
        )

        /* ==========================================
            RECRUITER PERSPECTIVE
           ========================================== */

        .recruiterFirstImpression(
                analysis.getRecruiterFirstImpression()
        )

        .recruiterOpinion(
                analysis.getRecruiterOpinion()
        )

        .recruiterConfidence(
                analysis.getRecruiterConfidence()
        )

        /* ==========================================
                RESUME POTENTIAL
           ========================================== */

        .growthPotential(
                analysis.getGrowthPotential()
        )

        .potentialResumeScore(
                analysis.getPotentialResumeScore()
        )

        .careerProjection(
                analysis.getCareerProjection()
        )

        /* ==========================================
                NEXT MILESTONE
           ========================================== */

        .nextMilestone(
                analysis.getNextMilestone()
        )

        .guidePreview(
                analysis.getGuidePreview()
        )

        .readyForResumeGuide(
                analysis.getReadyForResumeGuide()
        )

        /* ==========================================
                MISSING COMPONENTS
           ========================================== */

        .missingResumeComponents(
                analysis.getMissingResumeComponents()
        )

        /* ==========================================
                EXTRA
           ========================================== */

        .resumeConfidence(
                analysis.getResumeConfidence()
        )

        .oneLineResumeVerdict(
                analysis.getOneLineResumeVerdict()
        )

        .analysisVersion(
                analysis.getAnalysisVersion()
        )

        .build();
}

}