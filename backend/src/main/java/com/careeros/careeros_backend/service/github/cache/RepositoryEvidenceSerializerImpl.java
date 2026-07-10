package com.careeros.careeros_backend.service.github.cache;

import com.careeros.careeros_backend.dto.github.RepositoryEvidenceResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepositoryEvidenceSerializerImpl
        implements RepositoryEvidenceSerializer {

    private final ObjectMapper objectMapper;

    @Override
    public String serialize(
            RepositoryEvidenceResponse evidence
    ) {

        try {

            return objectMapper.writeValueAsString(
                    evidence
            );

        }
        catch (JsonProcessingException e) {

            throw new RuntimeException(
                    "Failed to serialize repository evidence.",
                    e
            );

        }

    }

    @Override
    public RepositoryEvidenceResponse deserialize(
            String json
    ) {

        try {

            return objectMapper.readValue(
                    json,
                    RepositoryEvidenceResponse.class
            );

        }
        catch (Exception e) {

            throw new RuntimeException(
                    "Failed to deserialize repository evidence.",
                    e
            );

        }

    }

}