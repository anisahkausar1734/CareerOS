package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.RoadmapResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoadmapService {

    private final GeminiService geminiService;

    public RoadmapResponse generateRoadmap(
            String targetRole
    ) {

        String prompt =
                "Create a learning roadmap for becoming a "
                        + targetRole
                        + ". "
                        + "Divide roadmap into phases. "
                        + "Use bullet points. "
                        + "Keep it concise and practical.";

        String roadmap =
                geminiService.askGemini(prompt);

        return new RoadmapResponse(
                roadmap
        );
    }
}