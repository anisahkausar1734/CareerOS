package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.InternshipAnalysisRequestDTO;
import com.careeros.careeros_backend.dto.InternshipAnalysisResponseDTO;
import com.careeros.careeros_backend.model.Resume;
import com.careeros.careeros_backend.model.StudentProfile;
import com.careeros.careeros_backend.repository.ResumeRepository;
import com.careeros.careeros_backend.repository.StudentProfileRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InternshipAnalysisService {


private final StudentProfileRepository
        studentProfileRepository;

private final ResumeRepository
        resumeRepository;

private final GeminiService
        geminiService;

public InternshipAnalysisResponseDTO
analyzeInternshipReadiness(
        InternshipAnalysisRequestDTO request
) 

{

if(
        request.getTargetRole() == null
        ||
        request.getTargetRole().isBlank()
) {

    throw new RuntimeException(
            "Target Role is required"
    );
}

    StudentProfile profile =
            studentProfileRepository
                    .findByEmail(
                            request.getEmail()
                    )
                    .orElseThrow(
                            () ->
                                    new RuntimeException(
                                            "Profile not found"
                                    )
                    );

    Resume resume =
            resumeRepository
                    .findByEmail(
                            request.getEmail()
                    )
                    .orElseThrow(
                            () ->
                                    new RuntimeException(
                                            "Resume not found"
                                    )
                    );

    String prompt =
            """


You are an internship hiring expert.

Current Year:
%s

Degree:
%s

Branch:
%s

Skills:
%s

Career Readiness:
%s

Dream Role:
%s

Target Internship Role:
%s

Target Company:
%s

Custom Requirements:
%s

Resume:
%s

Evaluate ONLY for internship hiring.

For VERDICT use ONLY one of:

Needs Significant Preparation

Partially Ready

Moderately Ready

Internship Ready

Highly Competitive Candidate

Consider:

- Student current year
- Student skill level
- Internship expectations
- Target role requirements
- Company requirements
- Custom requirements

Be realistic.

Do not give perfect scores unless the student is truly internship ready.

Return ONLY:

INTERNSHIP_SCORE=number

HIRING_PROBABILITY=number

VERDICT=text

ROLE_ALIGNMENT=number

PROJECT_STRENGTH=number

SKILL_READINESS=number

STRENGTHS=item1|item2|item3

MISSING_SKILLS=item1|item2|item3

MISSING_PROJECTS=item1|item2|item3

MISSING_CERTIFICATIONS=item1|item2|item3

MISSING_TOOLS=item1|item2|item3

COMPANY_EXPECTATIONS=item1|item2|item3

RECOMMENDATIONS=item1|item2|item3

Return ONLY the exact keys below.
Do not use bullets.
Do not use markdown.
Do not use labels.
Do not use colons.

Use | separator.
Never use bullets.
Never use markdown.
"""
.formatted(
profile.getCurrentYear(),
profile.getDegree(),
profile.getBranch(),
String.join(
        ", ",
        profile.getSkills()
),
profile.getCareerReadiness(),
profile.getDreamRole(),
request.getTargetRole(),
request.getTargetCompany(),
request.getCustomRequirements(),
resume.getResumeText()
      .substring(
          0,
          Math.min(
              resume.getResumeText().length(),
              3000
          )
      )
);

    String response =
            geminiService
                    .askGeminiCustom(
                            prompt
                    );

                    

System.out.println(
        "================================"
);

System.out.println(
        response
);

System.out.println(
        "================================"
);

    InternshipAnalysisResponseDTO result =
            new InternshipAnalysisResponseDTO();

    try {

        String[] lines =
                response.split("\n");

        for(String line : lines) {

line = line.replace(" = ", "=");
line = line.replace("= ", "=");

            if(line.startsWith(
                    "INTERNSHIP_SCORE="
            )) {

                result.setInternshipScore(
                        Integer.parseInt(
                                line.replace(
                                        "INTERNSHIP_SCORE=",
                                        ""
                                ).trim()
                        )
                );
            }

if(line.startsWith(
        "HIRING_PROBABILITY="
)) {

    result.setHiringProbability(
            Integer.parseInt(
                    line.replace(
                            "HIRING_PROBABILITY=",
                            ""
                    ).trim()
            )
    );
}

if(line.startsWith(
        "VERDICT="
)) {

    result.setVerdict(
            line.replace(
                    "VERDICT=",
                    ""
            ).trim()
    );
}


            if(line.startsWith(
                    "ROLE_ALIGNMENT="
            )) {

                result.setRoleAlignment(
                        Integer.parseInt(
                                line.replace(
                                        "ROLE_ALIGNMENT=",
                                        ""
                                ).trim()
                        )
                );
            }

            if(line.startsWith(
                    "PROJECT_STRENGTH="
            )) {

                result.setProjectStrength(
                        Integer.parseInt(
                                line.replace(
                                        "PROJECT_STRENGTH=",
                                        ""
                                ).trim()
                        )
                );
            }

            if(line.startsWith(
                    "SKILL_READINESS="
            )) {

                result.setSkillReadiness(
                        Integer.parseInt(
                                line.replace(
                                        "SKILL_READINESS=",
                                        ""
                                ).trim()
                        )
                );
            }

 if(line.startsWith(
                    "STRENGTHS="
            )) {

                result.setStrengths(
                        List.of(
                                line.replace(
                                        "STRENGTHS=",
                                        ""
                                ).split("\\|")
                        )
                );
            }

            if(line.startsWith(
                    "MISSING_SKILLS="
            )) {

                result.setMissingSkills(
                        List.of(
                                line.replace(
                                        "MISSING_SKILLS=",
                                        ""
                                ).split("\\|")
                        )
                );
            }

            if(line.startsWith(
                    "MISSING_PROJECTS="
            )) {

                result.setMissingProjects(
                        List.of(
                                line.replace(
                                        "MISSING_PROJECTS=",
                                        ""
                                ).split("\\|")
                        )
                );
            }

            if(line.startsWith(
                    "MISSING_CERTIFICATIONS="
            )) {

                result.setMissingCertifications(
                        List.of(
                                line.replace(
                                        "MISSING_CERTIFICATIONS=",
                                        ""
                                ).split("\\|")
                        )
                );
            }

            if(line.startsWith(
                    "MISSING_TOOLS="
            )) {

                result.setMissingTools(
                        List.of(
                                line.replace(
                                        "MISSING_TOOLS=",
                                        ""
                                ).split("\\|")
                        )
                );
            }

            if(line.startsWith(
                    "COMPANY_EXPECTATIONS="
            )) {

                result.setCompanyExpectations(
                        List.of(
                                line.replace(
                                        "COMPANY_EXPECTATIONS=",
                                        ""
                                ).split("\\|")
                        )
                );
            }

            if(line.startsWith(
                    "RECOMMENDATIONS="
            )) {

                result.setRecommendations(
                        List.of(
                                line.replace(
                                        "RECOMMENDATIONS=",
                                        ""
                                ).split("\\|")
                        )
                );
            }
        }

    } catch(Exception e) {

        e.printStackTrace();
    }

    

    return result;
}


}
