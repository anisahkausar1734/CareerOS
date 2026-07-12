package com.careeros.careeros_backend.service.projectanalysis.ai;

import com.careeros.careeros_backend.dto.projectanalysis.ProjectAnalysisResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EngineeringResponseParserImpl
        implements EngineeringResponseParser {

    private final ObjectMapper objectMapper;

    @Override
    public ProjectAnalysisResponse parse(
            String geminiResponse
    ) {

        try {

            return objectMapper.readValue(
                    clean(geminiResponse),
                    ProjectAnalysisResponse.class
            );

        }
      
catch (Exception e) {

    System.out.println("========== JACKSON ERROR ==========");
    e.printStackTrace();

    System.out.println("========== GEMINI RESPONSE ==========");
    System.out.println(geminiResponse);

    throw new RuntimeException(
            "Unable to parse engineering analysis.",
            e
    );

}

    }

    private String clean(
            String response
    ) {

        return response

                .replace("```json", "")

                .replace("```", "")

                .trim();

    }

}