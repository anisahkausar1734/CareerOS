package com.careeros.careeros_backend.service.ai.normalizer;

public interface GeminiResponseNormalizer {

    <T> T normalize(

            String response,

            Class<T> dtoClass

    );

}