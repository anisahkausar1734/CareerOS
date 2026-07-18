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


        System.out.println("\n========== ENGINEERING CONTEXT ==========");

System.out.println("Build Files: " + context.getBuildFiles());

System.out.println("Dependency Files: " + context.getDependencyFiles());

System.out.println("Facts: " + context.getFacts());

System.out.println("Signals: " + context.getSignals());

System.out.println("Capabilities: " + context.getCapabilities());

System.out.println("Has Docker: " + context.getHasDocker());

System.out.println("Has GitHub Actions: " + context.getHasGithubActions());

System.out.println("README Length: " +
        (context.getReadmeSummary() == null
                ? 0
                : context.getReadmeSummary().length()));
        String prompt =
                promptBuilder.buildPrompt(
                        context
                );

       String response =
        geminiService.askGeminiCustom(
                prompt
        );

System.out.println("========== GEMINI RESPONSE ==========");
System.out.println(response);
System.out.println("====================================");

return parser.parse(response);

    }

}