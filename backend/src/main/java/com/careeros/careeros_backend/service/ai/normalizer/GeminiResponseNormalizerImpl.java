package com.careeros.careeros_backend.service.ai.normalizer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeminiResponseNormalizerImpl
        implements GeminiResponseNormalizer {

    private final ObjectMapper objectMapper;

    private final JsonCleaner jsonCleaner;

    private final DtoFieldNormalizer dtoFieldNormalizer;

    @Override
    public <T> T normalize(

            String response,

            Class<T> dtoClass

    ) {

        try {

            /*
             * Clean Gemini response
             */
            String cleaned =
                    jsonCleaner.clean(response);

            /*
             * Parse into tree
             */
            JsonNode root =
                    objectMapper.readTree(cleaned);

            /*
             * Normalize according to DTO
             */
            dtoFieldNormalizer.normalize(
                    root,
                    dtoClass
            );

            /*
             * Convert directly into DTO
             */
            return objectMapper.treeToValue(
                    root,
                    dtoClass
            );

        }

        catch (Exception e) {

            throw new RuntimeException(
                    "Unable to normalize Gemini response.",
                    e
            );

        }

    }

}