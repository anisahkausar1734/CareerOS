package com.careeros.careeros_backend.service.github.parser;

import com.careeros.careeros_backend.dto.github.dependency.DependencyDescriptor;
import com.careeros.careeros_backend.dto.github.source.RepositorySourceFile;

import java.util.List;

public interface DependencyParser {

    boolean supports(
            RepositorySourceFile file
    );

    List<DependencyDescriptor> parse(
            RepositorySourceFile file
    );

}