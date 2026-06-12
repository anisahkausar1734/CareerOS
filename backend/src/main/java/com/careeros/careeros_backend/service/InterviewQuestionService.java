package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.InterviewQuestionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterviewQuestionService {

    private final GeminiService geminiService;

    public String generateQuestions(
            InterviewQuestionRequest request
    ) {

        String prompt =
                "Generate 10 interview questions for a "
                        + request.getLevel()
                        + " "
                        + request.getRole()
                        + ". "
                        + "Return only the questions. "
                        + "One question per line. "
                        + "Do not use markdown symbols.";

        return geminiService.askGemini(prompt);
    }
}