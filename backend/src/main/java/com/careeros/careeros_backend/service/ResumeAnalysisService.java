package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.ResumeAnalysisResponse;
import com.careeros.careeros_backend.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeAnalysisService {

    private final ResumeRepository resumeRepository;

    public ResumeAnalysisResponse analyzeResume(
            String email
    ) {

        List<String> extractedSkills = List.of(
                "Java",
                "Spring Boot",
                "MongoDB",
                "Git"
        );

        String education =
                "B.Tech Computer Science";

        Integer projectCount = 3;

        int resumeScore = 0;

        if (!extractedSkills.isEmpty()) {
            resumeScore += 40;
        }

        if (education != null &&
                !education.isBlank()) {
            resumeScore += 20;
        }

        if (projectCount >= 3) {
            resumeScore += 40;
        }

        return ResumeAnalysisResponse.builder()
                .skills(extractedSkills)
                .education(education)
                .projectCount(projectCount)
                .resumeScore(resumeScore)
                .build();
    }
}