package com.careeros.careeros_backend.service.ai.normalizer;

import com.fasterxml.jackson.databind.JsonNode;

public interface NumericFieldNormalizer {

    void normalize(
            JsonNode root
    );

}