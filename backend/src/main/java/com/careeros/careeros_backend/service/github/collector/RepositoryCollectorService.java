package com.careeros.careeros_backend.service.github.collector;

import com.careeros.careeros_backend.dto.github.RepositoryRawEvidence;

public interface RepositoryCollectorService {

    RepositoryRawEvidence collect(
            String githubUrl
    );

}