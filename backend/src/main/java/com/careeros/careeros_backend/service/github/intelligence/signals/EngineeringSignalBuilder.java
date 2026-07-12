package com.careeros.careeros_backend.service.github.intelligence.signals;

import com.careeros.careeros_backend.dto.github.intelligence.facts.RepositoryFact;
import com.careeros.careeros_backend.dto.github.intelligence.signals.EngineeringSignal;


import java.util.List;

public interface EngineeringSignalBuilder {

    List<EngineeringSignal> build(
            List<RepositoryFact> facts
    );

}