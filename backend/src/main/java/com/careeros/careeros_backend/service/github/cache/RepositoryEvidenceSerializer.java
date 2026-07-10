package com.careeros.careeros_backend.service.github.cache;

import com.careeros.careeros_backend.dto.github.RepositoryEvidenceResponse;

public interface RepositoryEvidenceSerializer {

    String serialize(
            RepositoryEvidenceResponse evidence
    );

    RepositoryEvidenceResponse deserialize(
            String json
    );

}