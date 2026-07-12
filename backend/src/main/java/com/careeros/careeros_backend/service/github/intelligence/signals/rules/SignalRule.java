package com.careeros.careeros_backend.service.github.intelligence.signals.rules;

import com.careeros.careeros_backend.dto.github.intelligence.facts.RepositoryFact;
import com.careeros.careeros_backend.dto.github.intelligence.signals.EngineeringSignal;

import java.util.List;

public interface SignalRule {

    EngineeringSignal evaluate(
            List<RepositoryFact> facts
    );

}