package com.careeros.careeros_backend.service.github.analyzer;

import com.careeros.careeros_backend.dto.github.intelligence.facts.RepositoryFact;
import com.careeros.careeros_backend.dto.github.source.RepositorySourceFile;

import java.util.List;

public interface RepositoryAnalyzer {

    boolean supports(
            RepositorySourceFile file
    );

    List<RepositoryFact> analyze(
            RepositorySourceFile file
    );

}