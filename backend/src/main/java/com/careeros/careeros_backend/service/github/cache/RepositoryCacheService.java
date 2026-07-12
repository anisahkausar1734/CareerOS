package com.careeros.careeros_backend.service.github.cache;

import com.careeros.careeros_backend.dto.github.RepositoryEvidenceResponse;

public interface RepositoryCacheService {

    RepositoryEvidenceResponse getRepositoryEvidence(
            String githubUrl,
            boolean forceRefresh
    );

}