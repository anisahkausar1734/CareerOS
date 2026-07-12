package com.careeros.careeros_backend.service.github.intelligence.facts;

import com.careeros.careeros_backend.dto.github.intelligence.facts.RepositoryFact;
import com.careeros.careeros_backend.dto.github.snapshot.RepositorySnapshot;

import java.util.List;

public interface RepositoryFactExtractor {

    List<RepositoryFact> extract(
            RepositorySnapshot snapshot
    );

}