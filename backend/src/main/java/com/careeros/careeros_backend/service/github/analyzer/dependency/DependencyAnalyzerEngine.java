package com.careeros.careeros_backend.service.github.analyzer.dependency;

import com.careeros.careeros_backend.dto.github.source.RepositorySourceFile;
import com.careeros.careeros_backend.dto.github.technology.DetectedTechnology;

import java.util.List;

public interface DependencyAnalyzerEngine {

    List<DetectedTechnology> analyze(
            List<RepositorySourceFile> files
    );

}