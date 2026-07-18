package com.careeros.careeros_backend.service.github.technology;

import com.careeros.careeros_backend.dto.github.dependency.DependencyDescriptor;
import com.careeros.careeros_backend.dto.github.technology.TechnologyDescriptor;

import java.util.Optional;

public interface TechnologyRegistry {

    Optional<TechnologyDescriptor> resolve(
            DependencyDescriptor dependency
    );

}