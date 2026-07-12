package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.*;
import com.careeros.careeros_backend.model.CareerInsight;
import com.careeros.careeros_backend.repository.CareerInsightRepository;
import com.careeros.careeros_backend.service.CareerInsightService;
import com.careeros.careeros_backend.service.InternshipReadinessService;
import com.careeros.careeros_backend.service.JobReadinessService;
import com.careeros.careeros_backend.service.ProjectService;
import com.careeros.careeros_backend.service.ResumeAnalysisService;
import com.careeros.careeros_backend.service.RoadmapService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CareerInsightServiceImpl
        implements CareerInsightService {

    private final ResumeAnalysisService
            resumeAnalysisService;

    private final RoadmapService
            roadmapService;

    private final InternshipReadinessService
            internshipReadinessService;

    private final JobReadinessService
            jobReadinessService;

    private final GeminiService
        geminiService;

    private final ProjectService
            projectService;
 
    private final ObjectMapper
        objectMapper;
        
    private final CareerInsightRepository careerInsightRepository;    



    

    @Override
    public CareerInsightResponse generateInsight(
            String email
    )
    {
       CareerInsightResponse cached =
        getCachedInsight(email);

if(cached != null)
{
    return cached;
}

        ResumeAnalysisResponse resume =
        resumeAnalysisService
                .getCachedAnalysis(email);

RoadmapResponse roadmap =
        roadmapService
                .getCachedRoadmap(email);

InternshipReadinessResponse internship =
        internshipReadinessService
                .getReadiness(email);

JobReadinessResponse job =
        jobReadinessService
                .getReadiness(email);

ProjectIntelligenceResponse project =
        projectService
                .getProjectIntelligence(email);

                String strongestArea = "Resume";

int strongestScore =
        resume.getResumeScore();

if(
        internship.getReadinessScore()
        > strongestScore
)
{
    strongestArea = "Internship";
    strongestScore =
            internship.getReadinessScore();
}

if(
        job.getReadinessScore()
        > strongestScore
)
{
    strongestArea = "Job";
    strongestScore =
            job.getReadinessScore();
}


String weakestArea = "Resume";

int weakestScore =
        resume.getResumeScore();

if(
        internship.getReadinessScore()
        < weakestScore
)
{
    weakestArea = "Internship";
    weakestScore =
            internship.getReadinessScore();
}

if(
        job.getReadinessScore()
        < weakestScore
)
{
    weakestArea = "Job";
    weakestScore =
            job.getReadinessScore();
}


String prompt = """
You are CareerOS AI Career Advisor.

Analyze the student's profile.

Target Role:
%s

Resume Score:
%d

ATS Score:
%d

Project Score:
%d

Job Readiness:
%d

Internship Readiness:
%d

Missing Skills:
%s

Priority Skills:
%s

Best Project:
%s

Recommended Next Action:
%s

Generate:

KEY_INSIGHT:
BIGGEST_RISK:
BIGGEST_OPPORTUNITY:
RECOMMENDED_FOCUS:
EXPECTED_OUTCOME:
CAREER_SUMMARY:

Keep answers concise.

"""
.formatted(
        roadmap.getDreamRole(),
        resume.getResumeScore(),
        resume.getAtsScore(),
        job.getReadinessScore(),
        internship.getReadinessScore(),
        roadmap.getTopPrioritySkills(),
        roadmap.getNextAction()
);

String geminiResponse =
        geminiService
                .askGeminiCustom(
                        prompt
                );

                System.out.println(geminiResponse);

              
String keyInsight =
        extract(
                geminiResponse,
                "KEY_INSIGHT:"
        );

String biggestRisk =
        extract(
                geminiResponse,
                "BIGGEST_RISK:"
        );

String biggestOpportunity =
        extract(
                geminiResponse,
                "BIGGEST_OPPORTUNITY:"
        );

String recommendedFocus =
        extract(
                geminiResponse,
                "RECOMMENDED_FOCUS:"
        );

String expectedOutcome =
        extract(
                geminiResponse,
                "EXPECTED_OUTCOME:"
        );

String careerSummary =
        extract(
                geminiResponse,
                "CAREER_SUMMARY:"
        );




   CareerInsightResponse response =
        CareerInsightResponse
                .builder()

                .strongestArea(
                        strongestArea
                )

                .weakestArea(
                        weakestArea
                )

                .keyInsight(
                        keyInsight
                )

                .biggestRisk(
                        biggestRisk
                )

                .biggestOpportunity(
                        biggestOpportunity
                )

                .recommendedFocus(
                        recommendedFocus
                )

                .expectedOutcome(
                        expectedOutcome
                )

                .careerSummary(
                        careerSummary
                )

                .build();

try {

    CareerInsight insight =
            CareerInsight
                    .builder()

                    .email(email)

                    .insightJson(
                            objectMapper
                                    .writeValueAsString(
                                            response
                                    )
                    )

                    .createdAt(
                            java.time.LocalDateTime.now()
                    )

                    .build();

    careerInsightRepository.save(
            insight
    );

}
catch (Exception e)
{
    e.printStackTrace();
}



return response;

    }



public CareerInsightResponse getCachedInsight(
        String email
)
{
    return careerInsightRepository
            .findByEmail(email)

            .map(
                    insight -> {

                        try {

                            return objectMapper
                                    .readValue(
                                            insight.getInsightJson(),
                                            CareerInsightResponse.class
                                    );

                        }
                        catch (Exception e)
                        {
                            return null;
                        }

                    }
            )

            .orElse(null);
}


@Override
public CareerInsightResponse regenerateInsight(
        String email
)
{
    careerInsightRepository.deleteByEmail(
            email
    );

    return generateInsight(
            email
    );
}

      private String extract(
        String text,
        String key
)
{
    for(
        String line :
        text.split("\n")
    )
    {
        if(
            line.startsWith(
                    key
            )
        )
        {
            return line.replace(
                    key,
                    ""
            ).trim();
        }
    }

    return "";
}
    
    
}