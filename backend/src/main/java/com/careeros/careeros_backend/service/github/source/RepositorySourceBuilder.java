package com.careeros.careeros_backend.service.github.source;

import com.careeros.careeros_backend.dto.github.snapshot.RepositorySnapshot;
import com.careeros.careeros_backend.dto.github.source.RepositorySourceFile;

import java.util.List;

public interface RepositorySourceBuilder {

    List<RepositorySourceFile> build(
            RepositorySnapshot snapshot
    );

}