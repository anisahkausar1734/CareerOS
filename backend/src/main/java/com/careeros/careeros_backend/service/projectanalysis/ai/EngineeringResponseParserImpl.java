package com.careeros.careeros_backend.service.projectanalysis.ai;

import com.careeros.careeros_backend.dto.projectanalysis.ProjectAnalysisResponse;
import com.careeros.careeros_backend.service.ai.normalizer.GeminiResponseNormalizer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class EngineeringResponseParserImpl
        implements EngineeringResponseParser {

    private final GeminiResponseNormalizer
            responseNormalizer;

    @Override
    public ProjectAnalysisResponse parse(
            String geminiResponse
    ) {

        return responseNormalizer.normalize(

                geminiResponse,

                ProjectAnalysisResponse.class

        );

    }

}