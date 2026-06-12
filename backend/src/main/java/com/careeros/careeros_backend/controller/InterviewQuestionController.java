package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.InterviewQuestionRequest;
import com.careeros.careeros_backend.dto.InterviewQuestionResponse;
import com.careeros.careeros_backend.service.InterviewQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interview")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class InterviewQuestionController {

    private final InterviewQuestionService
            interviewQuestionService;

    @PostMapping("/generate")
    public InterviewQuestionResponse
    generateQuestions(
            @RequestBody
            InterviewQuestionRequest request
    ) {

        String questions =
                interviewQuestionService
                        .generateQuestions(request);

        return new InterviewQuestionResponse(
                questions
        );
    }
}