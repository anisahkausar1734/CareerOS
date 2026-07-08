package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.CopilotResponse;
import com.careeros.careeros_backend.model.User;
import com.careeros.careeros_backend.repository.ApplicationRepository;
import com.careeros.careeros_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CareerCopilotService {

    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    private final GeminiService geminiService;

   public CopilotResponse ask(
        String email,
        String question,
        List<String> history
) {

        User user =
                userRepository.findByEmail(email)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "User not found"
                                )
                        );

        long applications =
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

                String conversationHistory =
        history == null
                ? ""
                : String.join(
                        "\n",
                        history
                );

        String prompt = """
You are CareerOS Copilot.

Use the user's profile and career data to answer.

User Profile:

Name: %s
Target Role: %s
Experience Level: %s
Dream Company: %s
Skills: %s

Applications: %d
Interviews: %d
Offers: %d
Conversation History:%s
User Question:
%s

Provide practical career guidance.
Do not use markdown.
Keep answer concise.
"""
.formatted(
        user.getFullName(),
        user.getTargetRole(),
        user.getExperienceLevel(),
        user.getDreamCompany(),
        user.getSkills(),
        applications,
        interviews,
        offers,
        conversationHistory,
        question
);

        String answer =
                geminiService.askGeminiCustom(
                        prompt
                );

        return CopilotResponse.builder()
                .response(answer)
                .build();
    }
}