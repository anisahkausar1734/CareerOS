package com.careeros.careeros_backend.service.github.intelligence.signals;

import com.careeros.careeros_backend.dto.github.intelligence.facts.RepositoryFact;
import com.careeros.careeros_backend.dto.github.intelligence.signals.EngineeringSignal;
import com.careeros.careeros_backend.service.github.intelligence.signals.rules.SignalRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EngineeringSignalBuilderImpl
        implements EngineeringSignalBuilder {

    private final List<SignalRule> rules;

    @Override
    public List<EngineeringSignal> build(
            List<RepositoryFact> facts
    ) {

        return rules.stream()

                .map(rule -> rule.evaluate(facts))

                .filter(Objects::nonNull)

                .toList();

    }

}