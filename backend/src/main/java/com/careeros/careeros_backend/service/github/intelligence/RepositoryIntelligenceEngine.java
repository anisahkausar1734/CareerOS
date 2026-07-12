package com.careeros.careeros_backend.service.github.intelligence;

import com.careeros.careeros_backend.dto.github.intelligence.RepositoryIntelligence;
import com.careeros.careeros_backend.dto.github.snapshot.RepositorySnapshot;

public interface RepositoryIntelligenceEngine {

    RepositoryIntelligence build(
            RepositorySnapshot snapshot
    );

}