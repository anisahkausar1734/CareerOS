package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.GeminiRequest;
import com.careeros.careeros_backend.dto.GeminiResponse;
import com.careeros.careeros_backend.service.GeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gemini")
@RequiredArgsConstructor
public class GeminiController {

    private final GeminiService geminiService;

    @PostMapping("/ask")
    public GeminiResponse ask(
            @RequestBody
            GeminiRequest request
    ) {

        String answer =
                geminiService.askGemini(
                        request.getPrompt()
                );

        return new GeminiResponse(
                answer
        );
    }
}