package com.careeros.careeros_backend.service.github.evidence;

import com.careeros.careeros_backend.dto.github.RepositoryEvidenceResponse;

public interface RepositoryEvidenceService {

    RepositoryEvidenceResponse collect(
        String githubUrl,
        boolean forceRefresh
);

}