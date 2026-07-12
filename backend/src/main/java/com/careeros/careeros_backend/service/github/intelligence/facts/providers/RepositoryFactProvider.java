package com.careeros.careeros_backend.service.github.intelligence.facts.providers;

import com.careeros.careeros_backend.dto.github.intelligence.facts.RepositoryFact;
import com.careeros.careeros_backend.dto.github.snapshot.RepositorySnapshot;

import java.util.List;

public interface RepositoryFactProvider {

    List<RepositoryFact> extract(
            RepositorySnapshot snapshot
    );

}