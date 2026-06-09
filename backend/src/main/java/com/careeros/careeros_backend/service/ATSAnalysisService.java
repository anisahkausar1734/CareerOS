package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.ATSAnalysisResponse;
import com.careeros.careeros_backend.dto.ATSImprovement;
import com.careeros.careeros_backend.dto.ResumeSectionStatus;
import com.careeros.careeros_backend.exception.UserNotFoundException;
import com.careeros.careeros_backend.model.User;
import com.careeros.careeros_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ATSAnalysisService {

    private final UserRepository userRepository;

    private List<String> getRequiredKeywords() {

        return List.of(
                "Java",
                "Spring Boot",
                "REST API",
                "Git",
                "Docker",
                "SQL"
        );
    }

    public ATSAnalysisResponse analyzeResume(
            String email
    ) {

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow(
                                () -> new UserNotFoundException(
                                        "User not found"
                                )
                        );

        List<String> skills =
                user.getSkills() != null
                        ? user.getSkills()
                        : List.of();

        List<String> missingKeywords =
                new ArrayList<>();

        for (String keyword : getRequiredKeywords()) {

            if (!skills.contains(keyword)) {
                missingKeywords.add(keyword);
            }
        }

        int atsScore =
                100 - (missingKeywords.size() * 10);

        atsScore = Math.max(atsScore, 0);

        return ATSAnalysisResponse.builder()
                .atsScore(atsScore)
                .missingKeywords(missingKeywords)
                .missingSections(List.of())
                .sectionStatus(
                        getSectionStatus(user)
                )
                .improvements(
                        generateImprovements(
                                missingKeywords
                        )
                )
                .feedback(
                        atsScore >= 80
                                ? "Strong ATS profile"
                                : "Add missing keywords"
                )
                .build();
    }

    public ATSAnalysisResponse analyzeResume(
            List<String> resumeSkills
    ) {

        List<String> missingKeywords =
                new ArrayList<>();

        for (String keyword : getRequiredKeywords()) {

            if (!resumeSkills.contains(keyword)) {
                missingKeywords.add(keyword);
            }
        }

        int atsScore =
                100 - (missingKeywords.size() * 10);

        atsScore = Math.max(atsScore, 0);

        return ATSAnalysisResponse.builder()
                .atsScore(atsScore)
                .missingKeywords(missingKeywords)
                .missingSections(List.of())
                .improvements(
                        generateImprovements(
                                missingKeywords
                        )
                )
                .feedback(
                        atsScore >= 80
                                ? "Strong ATS profile"
                                : "Add missing keywords"
                )
                .build();
    }

    private List<ATSImprovement> generateImprovements(
            List<String> missingKeywords
    ) {

        List<ATSImprovement> improvements =
                new ArrayList<>();

        for (String keyword : missingKeywords) {

            improvements.add(
                    ATSImprovement.builder()
                            .issue(
                                    "Missing " + keyword
                            )
                            .suggestion(
                                    "Add experience with "
                                            + keyword
                            )
                            .build()
            );
        }

        return improvements;
    }

    private List<ResumeSectionStatus> getSectionStatus(
            User user
    ) {

        return List.of(

                ResumeSectionStatus.builder()
                        .sectionName("Skills")
                        .present(
                                user.getSkills() != null
                                        && !user.getSkills().isEmpty()
                        )
                        .build(),

                ResumeSectionStatus.builder()
                        .sectionName("GitHub")
                        .present(
                                user.getGithubUrl() != null
                                        && !user.getGithubUrl().isBlank()
                        )
                        .build(),

                ResumeSectionStatus.builder()
                        .sectionName("LinkedIn")
                        .present(
                                user.getLinkedinUrl() != null
                                        && !user.getLinkedinUrl().isBlank()
                        )
                        .build()
        );
    }
}