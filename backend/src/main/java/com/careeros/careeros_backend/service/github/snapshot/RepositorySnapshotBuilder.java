package com.careeros.careeros_backend.service.github.snapshot;

import com.careeros.careeros_backend.dto.github.RepositoryRawEvidence;
import com.careeros.careeros_backend.dto.github.snapshot.RepositorySnapshot;

public interface RepositorySnapshotBuilder {

    RepositorySnapshot build(
            RepositoryRawEvidence rawEvidence
    );

}