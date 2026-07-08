package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.*;
import com.careeros.careeros_backend.model.StudentProfile;
import com.careeros.careeros_backend.repository.StudentProfileRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InternshipMatchService {

    private final StudentProfileRepository
            studentProfileRepository;

    private final GeminiService
            geminiService;

    public InternshipMatchResponseDTO analyzeMatch(
            InternshipMatchRequestDTO request
    ) {

System.out.println("STEP 1");

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

System.out.println("STEP 2");

        String prompt =
"""
You are an internship hiring expert.

Student Skills:
%s

Current Year:
%s

Dream Role:
%s

Internship Company:
%s

Internship Role:
%s

Internship Description:
%s

Return ONLY:

MATCH_SCORE=number

HIRING_PROBABILITY=number

VERDICT=text

STRENGTHS=item1|item2|item3

MISSING_SKILLS=item1|item2|item3

RECOMMENDATIONS=item1|item2|item3

Use | separator.
No markdown.
"""
.formatted(
        profile.getSkills(),
        profile.getCurrentYear(),
        profile.getDreamRole(),
        request.getCompany(),
        request.getRole(),
        request.getDescription()
);

System.out.println("STEP 3");

        String response =
                geminiService
                        .askGeminiCustom(
                                prompt
                        );

System.out.println("STEP 4");

                        if(response == null) {

    throw new RuntimeException(
            "Gemini returned null"
    );

}

                System.out.println(
        "========== GEMINI RESPONSE =========="
);

System.out.println(response);

System.out.println(
        "====================================="
);    



        InternshipMatchResponseDTO
                result =
                InternshipMatchResponseDTO
                        .builder()
                        .build();

        try {

            String[] lines =
                    response.split("\n");

            for(String line : lines) {

                if(line.startsWith(
                        "MATCH_SCORE="
                )) {

                    result.setMatchScore(
                            Integer.parseInt(
                                    line.replace(
                                            "MATCH_SCORE=",
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