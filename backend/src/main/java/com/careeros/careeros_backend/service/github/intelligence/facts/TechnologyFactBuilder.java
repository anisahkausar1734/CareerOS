package com.careeros.careeros_backend.service.github.intelligence.facts;

import com.careeros.careeros_backend.dto.github.intelligence.facts.RepositoryFact;
import com.careeros.careeros_backend.dto.github.technology.DetectedTechnology;

import java.util.List;

public interface TechnologyFactBuilder {

    List<RepositoryFact> build(
            List<DetectedTechnology> technologies
    );

}