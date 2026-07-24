package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.ResumeRefinementHistoryDTO;
import com.careeros.careeros_backend.dto.ResumeRefinementRequestDTO;
import com.careeros.careeros_backend.dto.ResumeRefinementResponseDTO;
import com.careeros.careeros_backend.model.Resume;
import com.careeros.careeros_backend.model.ResumeRefinement;
import com.careeros.careeros_backend.repository.ResumeRefinementRepository;
import com.careeros.careeros_backend.repository.ResumeRepository;
import java.util.List;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ResumeRefinementService {

    private final ResumeRepository
            resumeRepository;

    private final GeminiService
            geminiService;

    private final ResumeRefinementRepository
        resumeRefinementRepository;        

    public ResumeRefinementResponseDTO
    refineResume(
            ResumeRefinementRequestDTO request
    ) {

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

                        System.out.println(
        "RESUME FOUND = "
        + resume.getResumeFileName()
);

System.out.println(
        "EMAIL = "
        + request.getEmail()
);

        String prompt =
                """
You are an expert resume writer.

Resume:
%s

Target Company:
%s

Job Description:
%s

Custom Prompt:
%s

You are an expert ATS resume editor.

IMPORTANT RULES:

1. Use ONLY information already present in the resume.
2. Do NOT invent companies.
3. Do NOT invent projects.
4. Do NOT invent internships.
5. Do NOT invent certifications.
6. Do NOT add placeholders.
7. Do NOT use text like:
   [Your Name]
   [Your Email]
   [Company Name]
8. Keep all existing resume content.
9. Improve wording and ATS optimization only.
10. Tailor to the target company and job description.

Target Company:
%s

Job Description:
%s

Custom Instructions:
%s

Resume:
%s

Return:

SUMMARY=
...

ATS_KEYWORDS=
keyword1|keyword2|keyword3

CHANGES_MADE=
change1|change2|change3

REFINED_RESUME=
(full improved resume)

Return ONLY:

SUMMARY=one sentence

ATS_KEYWORDS=item1|item2|item3|item4

CHANGES_MADE=item1|item2|item3|item4

REFINED_RESUME=full refined resume

Use | separator.

Do not use markdown.
"""
                        .formatted(
                                resume.getResumeText(),
                                request.getCompanyName(),
                                request.getJobDescription(),
                                request.getCustomPrompt()
                        );

System.out.println(
        "CALLING GEMINI..."
);

        String response =
                geminiService
                        .askGeminiCustom(
                                prompt
                        );

                        System.out.println(
        "========== GEMINI RESPONSE =========="
);

System.out.println(
        response
);

System.out.println(
        "====================================="
);

        ResumeRefinementResponseDTO result =
                new ResumeRefinementResponseDTO();

        try {

            String[] lines =
                    response.split("\n");

            StringBuilder resumeText =
                    new StringBuilder();

            boolean collectingResume =
                    false;

            for(String line : lines) {

                if(line.startsWith(
                        "SUMMARY="
                )) {

                    result.setSummary(
                            line.replace(
                                    "SUMMARY=",
                                    ""
                            )
                    );
                }

                else if(line.startsWith(
                        "ATS_KEYWORDS="
                )) {

                    result.setAtsKeywords(
                            List.of(
                                    line.replace(
                                            "ATS_KEYWORDS=",
                                            ""
                                    ).split("\\|")
                            )
                    );
                }

                else if(line.startsWith(
                        "CHANGES_MADE="
                )) {

                    result.setChangesMade(
                            List.of(
                                    line.replace(
                                            "CHANGES_MADE=",
                                            ""
                                    ).split("\\|")
                            )
                    );
                }

                else if(line.startsWith(
                        "REFINED_RESUME="
                )) {

                    collectingResume = true;

                    resumeText.append(
                            line.replace(
                                    "REFINED_RESUME=",
                                    ""
                            )
                    ).append("\n");
                }

                else if(collectingResume) {

                    resumeText.append(
                            line
                    ).append("\n");
                }

            }

            result.setRefinedResume(
                    resumeText.toString()
            );

       } catch(Exception e) {

    System.out.println(
        "==================== REFINEMENT ERROR ===================="
    );

    e.printStackTrace();

    System.out.println(
        "=========================================================="
    );

    throw new RuntimeException(
        e.getMessage()
    );
}


ResumeRefinement refinement =
        ResumeRefinement.builder()
                .email(
                        request.getEmail()
                )
                .companyName(
                        request.getCompanyName()
                )
                .jobDescription(
                        request.getJobDescription()
                )
                .customPrompt(
                        request.getCustomPrompt()
                )
                .summary(
                        result.getSummary()
                )
                .atsKeywords(
                        result.getAtsKeywords()
                )
                .changesMade(
                        result.getChangesMade()
                )
                .refinedResume(
                        result.getRefinedResume()
                )
                .createdAt(
                        LocalDateTime.now()
                )
                .build();

resumeRefinementRepository.save(
        refinement
);

        return result;
    }
public List<ResumeRefinementHistoryDTO>
getHistory(
        String email
) {

    return resumeRefinementRepository
            .findByEmailOrderByCreatedAtDesc(
                    email
            )
            .stream()
            .map(item -> {

                ResumeRefinementHistoryDTO dto =
                        new ResumeRefinementHistoryDTO();

                dto.setId(
                        item.getId()
                );

                dto.setCompanyName(
                        item.getCompanyName()
                );

                dto.setCreatedAt(
                        item.getCreatedAt()
                );

                return dto;

            })
            .toList();           
}

public ResumeRefinement
getVersion(
        String id
) {

    return resumeRefinementRepository
            .findById(id)
            .orElseThrow(
                    () ->
                            new RuntimeException(
                                    "Version not found"
                            )
            );
}

}