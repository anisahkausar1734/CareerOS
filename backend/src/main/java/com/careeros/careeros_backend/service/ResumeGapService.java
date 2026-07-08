package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.ResumeAnalysisResponse;
import com.careeros.careeros_backend.dto.ResumeGapResponse;
import com.careeros.careeros_backend.model.StudentProfile;
import com.careeros.careeros_backend.repository.StudentProfileRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeGapService {

    private final StudentProfileRepository
            studentProfileRepository;

    private final ResumeAnalysisService
            resumeAnalysisService;

    private final GeminiService
            geminiService;

    public ResumeGapResponse analyzeGap(
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
                
        String prompt =
                """
You are an expert career coach.

Dream Role:
%s

Current Skills:
%s

Resume Skills:
%s

Current Year:
%s

Graduation Year:
%s

Return ONLY:

MATCH_PERCENTAGE=number

MISSING_SKILLS=item1|item2|item3

MISSING_PROJECTS=item1|item2|item3

MISSING_CERTIFICATIONS=item1|item2|item3

RECOMMENDATIONS=item1|item2|item3

Use | as separator.
Never use commas between items.
"""
                .formatted(
                        profile.getDreamRole(),
                        profile.getSkills(),
                        profile.getCurrentYear(),
                        profile.getGraduationYear()
                );

        String response =
                geminiService
                        .askGeminiCustom(prompt);

        ResumeGapResponse result =
                new ResumeGapResponse();

        try {

            String[] lines =
                    response.split("\n");

            for(String line : lines) {

                if(line.startsWith(
                        "MATCH_PERCENTAGE="
                )) {

                    result.setMatchPercentage(
                            Integer.parseInt(
                                    line.replace(
                                            "MATCH_PERCENTAGE=",
                                            ""
                                    ).trim()
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