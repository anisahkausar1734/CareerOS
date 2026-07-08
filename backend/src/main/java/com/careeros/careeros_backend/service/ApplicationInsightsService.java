package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.ApplicationInsightsResponse;
import com.careeros.careeros_backend.model.User;
import com.careeros.careeros_backend.repository.ApplicationRepository;
import com.careeros.careeros_backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationInsightsService {

    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    private final GeminiService geminiService;
    

    public ApplicationInsightsResponse analyze(
            String email
    ) {

        Optional<User> optionalUser =
                userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException(
                    "User not found"
            );
        }

        User user = optionalUser.get();

        long totalApplications =
                applicationRepository.countByEmail(email);

        long interviews =
                applicationRepository.countByEmailAndStatus(
                        email,
                        "INTERVIEW"
                );

        long offers =
                applicationRepository.countByEmailAndStatus(
                        email,
                        "OFFER"
                );

        long rejected =
                applicationRepository.countByEmailAndStatus(
                        email,
                        "REJECTED"
                );

        String prompt = """
You are an expert career coach.

Never use markdown.
Never use ** symbols.
Use plain text only.

Analyze the user's job search performance.

Target Role:
%s

Dream Company:
%s

Experience Level:
%s

Applications:
%d

Interviews:
%d

Offers:
%d

Rejected:
%d

Give:

1. Performance Summary

2. Strengths

3. Weaknesses

4. Actionable Recommendations

Keep response under 250 words.
"""
.formatted(
        user.getTargetRole(),
        user.getDreamCompany(),
        user.getExperienceLevel(),
        totalApplications,
        interviews,
        offers,
        rejected
);

        String analysis =
                geminiService.askGeminiCustom(prompt);

        return ApplicationInsightsResponse
                .builder()
                .analysis(analysis)
                .build();
    }
}