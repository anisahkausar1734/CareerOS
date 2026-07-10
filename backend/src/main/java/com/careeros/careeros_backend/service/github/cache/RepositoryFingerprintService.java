package com.careeros.careeros_backend.service.github.cache;

import com.careeros.careeros_backend.dto.github.RepositoryFingerprint;

public interface RepositoryFingerprintService {

    RepositoryFingerprint getFingerprint(
            String githubUrl
    );

}