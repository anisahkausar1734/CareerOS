package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.ResumeAnalysisResponse;
import com.careeros.careeros_backend.dto.RoadmapResponse;
import com.careeros.careeros_backend.dto.RoadmapStepResponse;
import com.careeros.careeros_backend.dto.SkillGapResponse;
import com.careeros.careeros_backend.model.Roadmap;
import com.careeros.careeros_backend.model.StudentProfile;
import com.careeros.careeros_backend.repository.ProjectRepository;
import com.careeros.careeros_backend.repository.RoadmapRepository;
import com.careeros.careeros_backend.repository.StudentProfileRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoadmapService {

    private final StudentProfileRepository
            studentProfileRepository;

    private final SkillGapService
        skillGapService;

private final ResumeAnalysisService
        resumeAnalysisService;

  private final ProjectRepository
        projectRepository;


 private final RoadmapRepository
        roadmapRepository;       
  
        private final ObjectMapper
        objectMapper;


public RoadmapResponse
getCachedRoadmap(
        String email
)
{
    return roadmapRepository
            .findByEmail(email)
            .map(
                    roadmap ->
                    {
                        try {

                            return objectMapper.readValue(
                                    roadmap.getRoadmapJson(),
                                    RoadmapResponse.class
                            );

                        } catch (Exception e) {

                            throw new RuntimeException(e);

                        }
                    }
            )
            .orElse(null);
}



public RoadmapResponse
getRoadmap(
        String email
)
{
    RoadmapResponse cached =
            getCachedRoadmap(
                    email
            );

    if(cached != null)
    {
        return cached;
    }

    return generateRoadmap(
            email
    );
}


public RoadmapResponse
regenerateRoadmap(
        String email
)
{
    roadmapRepository
            .findByEmail(email)
            .ifPresent(
                    roadmapRepository::delete
            );

    return generateRoadmap(email);
}

private void saveRoadmap(
        String email,
        RoadmapResponse response
)
{
    roadmapRepository
            .findByEmail(email)
            .ifPresent(
                    roadmapRepository::delete
            );

    try {

        roadmapRepository.save(

                Roadmap.builder()
                        .email(email)
                        .roadmapJson(
                                objectMapper
                                        .writeValueAsString(
                                                response
                                        )
                        )
                        .build()

        );

    }
    catch(Exception e)
    {
        throw new RuntimeException(e);
    }
}


public RoadmapResponse
updateStepStatus(
        String email,
        Integer phase,
        Boolean completed
)
{
    RoadmapResponse roadmap =
            getCachedRoadmap(email);

    if(roadmap == null)
    {
        throw new RuntimeException(
                "Roadmap not found"
        );
    }

   roadmap
        .getRoadmapSteps()
        .forEach(
                step ->
                {
                    System.out.println(
                            "STEP PHASE = "
                            + step.getPhase()
                    );

                    if(
                            step.getPhase()
                                    .equals(phase)
                    )
                    {
                        System.out.println(
                                "MATCH FOUND"
                        );

                        step.setCompleted(
                                completed
                        );

                        System.out.println(
                                "UPDATED TO = "
                                + step.getCompleted()
                        );
                    }
                }
        );

    long completedCount =

            roadmap
                    .getRoadmapSteps()
                    .stream()
                    .filter(
                            step ->
                                    Boolean.TRUE.equals(
                                            step.getCompleted()
                                    )
                    )
                    .count();

    int progress =

            roadmap.getRoadmapSteps().isEmpty()

                    ? 0

                    : (int)(
                            completedCount * 100
                            /
                            roadmap.getRoadmapSteps().size()
                    );

    roadmap.setCompletionPercentage(
            progress
    );
    int remainingWeeks =

        roadmap
                .getRoadmapSteps()
                .stream()
                .filter(
                        step ->
                                !Boolean.TRUE.equals(
                                        step.getCompleted()
                                )
                )
                .mapToInt(
                        RoadmapStepResponse
                                ::getEstimatedWeeks
                )
                .sum();

                roadmap.setTotalWeeks(
        remainingWeeks
);

    saveRoadmap(
            email,
            roadmap
    );
    

roadmap
        .getRoadmapSteps()
        .forEach(
                step ->
                        System.out.println(
                                step.getPhase()
                                + " -> "
                                + step.getCompleted()
                        )
        );

    return roadmap;
}
    public RoadmapResponse generateRoadmap(
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

        SkillGapResponse gap =
        skillGapService
                .getSkillGap(email);



                
ResumeAnalysisResponse resume =
        resumeAnalysisService
                .getCachedAnalysis(email);
          if(resume == null)
{
    resume =
            ResumeAnalysisResponse
                    .builder()
                    .resumeScore(0)
                    .atsScore(0)
                    .internshipReadiness(0)
                    .jobReadiness(0)
                    .projectStrengthScore(0)
                    .build();
}

              
                

       List<RoadmapStepResponse>
roadmapSteps =
buildExecutionPlan(
        email,
        profile,
        gap,
        resume
);

long completedSteps =

        roadmapSteps
                .stream()
                .filter(
                        step ->
                                Boolean.TRUE.equals(
                                        step.getCompleted()
                                )
                )
                .count();

int completionPercentage =

        roadmapSteps.isEmpty()

                ? 0

                : (int)(
                        completedSteps * 100
                        / roadmapSteps.size()
                );

int remainingWeeks =

        roadmapSteps
                .stream()
                .filter(
                        step ->
                                !Boolean.TRUE.equals(
                                        step.getCompleted()
                                )
                )
                .mapToInt(
                        RoadmapStepResponse::getEstimatedWeeks
                )
                .sum();

        profile.setRoadmapCompleted(
                true
        );

        profile.setCareerReadiness(
        calculateReadiness(profile)
);

        profile.setCurrentStage(
                "ROADMAP_COMPLETED"
        );

        studentProfileRepository
                .save(profile);






     RoadmapResponse response =
        RoadmapResponse
                .builder()
                .dreamRole(
                        profile.getDreamRole()
                )
                .estimatedMonths(6)
                .readinessScore(
        completionPercentage
)
                .roadmapSteps(
                        roadmapSteps
                )
                .resumeScore(
                        resume.getResumeScore()
                )
                .totalWeeks(
        remainingWeeks
)
                
                .atsScore(
                        resume.getAtsScore()
                )
                .internshipReadiness(
                        resume.getInternshipReadiness()
                )
                .jobReadiness(
                        resume.getJobReadiness()
                )
               .topPrioritySkills(
        gap.getPrioritySkills() == null
                ? List.of()
                : gap.getPrioritySkills()
)
                .nextAction(
                        getNextAction(
                                gap,
                                resume
                        )
                )
                .actionReason(
                        getActionReason(
                                gap,
                                resume
                        )
                )

                .expectedOutcome(
        getExpectedOutcome(
                gap,
                resume,
                profile
        )
)
                
                .recommendedProjects(
        getRecommendedProjects(
                profile.getDreamRole()
        )
)

.completionPercentage(
        completionPercentage
)

.build();

roadmapRepository
        .findByEmail(email)
        .ifPresent(
                roadmapRepository::delete
        );

                try {

    roadmapRepository.save(

            Roadmap.builder()
                    .email(email)
                    .roadmapJson(
                            objectMapper
                                    .writeValueAsString(
                                            response
                                    )
                    )
                    .build()

    );

}
catch(Exception e)
{
    throw new RuntimeException(e);
}

return response;

}

private String getExpectedOutcome(
        SkillGapResponse gap,
        ResumeAnalysisResponse resume,
        StudentProfile profile
)
{

    String nextSkill =
            gap.getPrioritySkills()
                    .isEmpty()
                    ? profile.getDreamRole()
                    : gap.getPrioritySkills()
                            .get(0);

    if(
        resume.getAtsScore() < 60
    )
    {
        return
            "Improved ATS visibility and higher interview opportunities for "
            + profile.getDreamRole();
    }

    if(
        gap.getPrioritySkills() != null
        &&
        !gap.getPrioritySkills().isEmpty()
    )
    {
        return
            "Stronger readiness for "
            + profile.getDreamRole()
            + " by mastering "
            + nextSkill;
    }

    return
        "Higher job readiness and stronger alignment with "
        + profile.getDreamRole();
}
 
private String getNextAction(
        SkillGapResponse gap,
        ResumeAnalysisResponse resume
) {

    if(resume.getAtsScore() < 70) {
        return "Improve ATS Score";
    }

    if(resume.getProjectStrengthScore() < 70) {
        return "Build Industry-Level Projects";
    }

    if(
            gap.getPrioritySkills() != null
            &&
            !gap.getPrioritySkills().isEmpty()
    ) {
        return "Learn "
                + gap.getPrioritySkills().get(0);
    }

    if(resume.getInternshipReadiness() < 70) {
        return "Improve Internship Readiness";
    }

    return "Start Applying";
}

private String getActionReason(
        SkillGapResponse gap,
        ResumeAnalysisResponse resume
) {

    if(resume.getAtsScore() < 70) {
        return "Low ATS score is limiting recruiter visibility.";
    }

    if(resume.getProjectStrengthScore() < 70) {
        return "Projects need stronger real-world impact.";
    }

    if(
            gap.getPrioritySkills() != null
            &&
            !gap.getPrioritySkills().isEmpty()
    ) {
        return "Missing skills are affecting career readiness.";
    }

    return "You are ready to start applying.";
}



private List<String> getRecommendedProjects(
        String dreamRole
) {

    if(dreamRole == null) {

        return List.of(
                "Portfolio Project"
        );
    }

    switch(
            dreamRole.toLowerCase()
    ) {

        case "ai engineer":

            return List.of(
                    "AI Resume Analyzer",
                    "Career Recommendation Engine",
                    "MLOps Pipeline"
            );

        case "backend developer":

            return List.of(
                    "Banking API",
                    "URL Shortener",
                    "E-Commerce Backend"
            );

        case "full stack developer":

            return List.of(
                    "Job Portal",
                    "E-Commerce Platform",
                    "Social Media App"
            );

        default:

            return List.of(
                    "Portfolio Project"
            );
    }
}


private String getSkillImpact(
        String skill
) {

    skill =
            skill.toLowerCase();

    if(skill.contains("tensorflow"))
        return "+10 AI Engineer Readiness";

    if(skill.contains("pytorch"))
        return "+10 AI Engineer Readiness";

    if(skill.contains("machine learning"))
        return "+8 Job Readiness";

    if(skill.contains("statistics"))
        return "+6 Foundation Strength";

    if(skill.contains("mathematics"))
        return "+6 Foundation Strength";

    return "+5 Career Readiness";
}

private List<RoadmapStepResponse>
buildExecutionPlan(
        String email,
        StudentProfile profile,
        SkillGapResponse gap,
        ResumeAnalysisResponse resume
) {

    List<RoadmapStepResponse> steps =
            new ArrayList<>();

    int phase = 1;

if(resume.getAtsScore() < 70) {

    steps.add(

            RoadmapStepResponse
                    .builder()
.priority("HIGH")
                    .phase(phase++)

                    .title(
                            "Improve ATS Score"
                    )

                    .category(
                            "ATS"
                    )

                    .reason(
                            "Current ATS score is below industry expectations."
                    )

                    .impact(
                            "+10 ATS Score"
                    )

                    .description(
                            "Add missing keywords, improve formatting and optimize resume sections."
                    )

                    .estimatedWeeks(1)


                    
.completed(false)


.resources(
                            List.of(
                                    "Resume Center",
                                    "ATS Analyzer"
                            )
                    )

                    .build()
    );
}

for(
        String project :
        getRecommendedProjects(
                profile.getDreamRole()
        )
) {

    steps.add(

            RoadmapStepResponse
                    .builder()

                    .phase(
                            phase++
                    )

                    .title(
                            "Build " + project
                    )

                    .category(
                            "PROJECT"
                    )

                    .priority(
                            "HIGH"
                    )

                    .reason(
                            "Project experience is required for industry readiness."
                    )

                    .impact(
                            "+8 Job Readiness"
                    )

                    .description(
        getProjectDescription(project)
)

                    .estimatedWeeks(4)

.completed(
        isProjectCompleted(
                email,
                project
        )
)


.resources(
        List.of(
                "GitHub Repository",
                "Portfolio Showcase",
                "Resume Center",
                "Deployment Platform"
        )
)
                    .build()
    );
}





if(
        gap.getPrioritySkills() != null
)
{

    for(
            String skill :
            gap.getPrioritySkills()
                    .stream()
                    .limit(5)
                    .toList()
    )
    {

        steps.add(

                RoadmapStepResponse
                        .builder()

                        .phase(
                                phase++
                        )

                        .title(
                                "Learn " + skill
                        )

                        .priority("MEDIUM")

                        .category(
                                "SKILL"
                        )

                        .reason(
                                "Missing skill identified by Skill Gap Analysis."
                        )

                        .impact(
                                getSkillImpact(skill)
                        )

                        .description(
                                getSkillDescription(skill)
                        )

                       .estimatedWeeks(3)

.completed(false)

.resources(
                                List.of(
                                        "Official Documentation",
                                        "Hands-on Project",
                                        "YouTube"
                                )
                        )

                        .build()
        );
    }
}
if(
        resume.getInternshipReadiness()
                < 70
) {

    steps.add(

            RoadmapStepResponse
                    .builder()

                    .phase(
                            phase++
                    )

                    .title(
                            "Improve Internship Readiness"
                    )
.priority("MEDIUM")
                    .category(
                            "INTERNSHIP"
                    )

                    .reason(
                            "Internship readiness score is below target."
                    )

                    .impact(
                            "+8 Internship Readiness"
                    )

                    .description(
                            "Complete projects, improve resume and practice interviews."
                    )

                   .estimatedWeeks(2)

.completed(false)

.resources(
                            List.of(
                                    "Mock Interview",
                                    "Resume Center"
                            )
                    )

                    .build()
    );
}

if(
        resume.getJobReadiness()
                >= 70
) {

    steps.add(

            RoadmapStepResponse
                    .builder()

                    .phase(
                            phase++
                    )

                    .title(
                            "Start Applying"
                    )
.priority("LOW")
                    .category(
                            "CAREER"
                    )

                    .reason(
                            "You are approaching job readiness."
                    )

                    .impact(
                            "Real Interview Opportunities"
                    )

                    .description(
                            "Begin applying for internships and entry-level positions."
                    )

                    .estimatedWeeks(1)

.completed(false)

.resources(
                            List.of(
                                    "Job Recommendations",
                                    "Application Tracker"
                            )
                    )

                    .build()
    );
}       

    return steps;
}


private boolean isProjectCompleted(
        String email,
        String projectName
)
{
    return projectRepository
            .findByEmail(email)
            .stream()
            .anyMatch(
                    project ->
                            project
                                    .getProjectName()
                                    .equalsIgnoreCase(
                                            projectName
                                    )
            );
}


private String getSkillDescription(
        String skill
) {

    skill = skill.toLowerCase();

    if(skill.contains("tensorflow"))
        return "Learn neural network development, model training and deployment using TensorFlow.";

    if(skill.contains("pytorch"))
        return "Build deep learning models and understand modern AI workflows using PyTorch.";

    if(skill.contains("machine learning"))
        return "Understand supervised, unsupervised and model evaluation techniques.";

    if(skill.contains("statistics"))
        return "Learn probability, distributions and statistical concepts required for AI.";

    if(skill.contains("mathematics"))
        return "Strengthen linear algebra, calculus and optimization fundamentals for ML.";

    return "Learn and apply " + skill + " through practical projects.";
}

private String getProjectDescription(
        String project
) {

    switch(project.toLowerCase()) {

        case "ai resume analyzer":

            return """
Build an AI-powered resume analyzer using Gemini API.
Implement resume scoring, ATS analysis and skill-gap detection.
Deploy it and add it to your portfolio.
""";

        case "career recommendation engine":

            return """
Build a recommendation engine that suggests roles,
skills and learning paths based on student profiles.
Use AI and data-driven matching logic.
""";

        case "mlops pipeline":

            return """
Build an end-to-end ML pipeline including training,
model versioning, deployment and monitoring.
Focus on production AI workflows.
""";

        case "banking api":

            return """
Build a secure banking backend with authentication,
transactions, account management and role-based access.
""";

        case "url shortener":

            return """
Build a scalable URL shortener with analytics,
custom links and database persistence.
""";

        case "e-commerce backend":

            return """
Build a production-grade backend supporting products,
orders, payments and JWT authentication.
""";

        default:

            return "Build and showcase " + project;
    }
}

private Integer calculateReadiness(
        StudentProfile profile
) {

    int score = 10;

    if(Boolean.TRUE.equals(
            profile.getSkillGapCompleted()
    )) score += 20;

    if(Boolean.TRUE.equals(
            profile.getRoadmapCompleted()
    )) score += 20;

    if(Boolean.TRUE.equals(
            profile.getResumeAnalysisCompleted()
    )) score += 20;

    if(Boolean.TRUE.equals(
            profile.getInterviewCompleted()
    )) score += 15;

    if(Boolean.TRUE.equals(
            profile.getApplicationsStarted()
    )) score += 15;

    return score;
}

   
    

}