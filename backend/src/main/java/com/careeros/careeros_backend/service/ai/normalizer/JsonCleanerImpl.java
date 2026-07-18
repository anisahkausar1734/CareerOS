package com.careeros.careeros_backend.service.ai.normalizer;

import org.springframework.stereotype.Service;

@Service
public class JsonCleanerImpl
        implements JsonCleaner {

    @Override
    public String clean(
            String response
    ) {

        if (response == null) {
            return "";
        }

        return response

                .replace("```json", "")

                .replace("```", "")

                .replace("\uFEFF", "")

                .replace("\u200B", "")

                .replace("\u00A0", " ")

                .trim();

    }

}