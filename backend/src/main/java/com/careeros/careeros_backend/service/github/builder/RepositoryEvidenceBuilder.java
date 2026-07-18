package com.careeros.careeros_backend.service.github.builder;

import com.careeros.careeros_backend.dto.github.RepositoryEvidenceResponse;
import com.careeros.careeros_backend.dto.github.RepositoryRawEvidence;
import com.careeros.careeros_backend.dto.github.intelligence.RepositoryIntelligence;
import com.careeros.careeros_backend.dto.github.snapshot.RepositorySnapshot;

public interface RepositoryEvidenceBuilder {
RepositoryEvidenceResponse build(
        RepositoryRawEvidence raw,
        RepositorySnapshot snapshot,
        RepositoryIntelligence intelligence
);

}