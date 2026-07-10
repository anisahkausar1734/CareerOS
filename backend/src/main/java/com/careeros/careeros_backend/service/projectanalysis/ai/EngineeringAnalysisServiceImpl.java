package com.careeros.careeros_backend.service.projectanalysis.ai;

import com.careeros.careeros_backend.dto.projectanalysis.ProjectAnalysisResponse;
import com.careeros.careeros_backend.dto.projectanalysis.context.EngineeringContext;
import com.careeros.careeros_backend.service.GeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EngineeringAnalysisServiceImpl
        implements EngineeringAnalysisService {

    private final EngineeringPromptBuilder
            promptBuilder;

    private final EngineeringResponseParser
            parser;

    private final GeminiService
            geminiService;

    @Override
    public ProjectAnalysisResponse analyze(
            EngineeringContext context
    ) {

        String prompt =
                promptBuilder.buildPrompt(
                        context
                );

        String response =
                geminiService.askGeminiCustom(
                        prompt
                );

        return parser.parse(
                response
        );

    }

}