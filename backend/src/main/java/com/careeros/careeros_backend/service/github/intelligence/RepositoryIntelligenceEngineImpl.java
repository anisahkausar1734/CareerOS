package com.careeros.careeros_backend.service.github.intelligence;

import com.careeros.careeros_backend.dto.github.intelligence.RepositoryIntelligence;
import com.careeros.careeros_backend.dto.github.intelligence.capabilities.EngineeringCapability;
import com.careeros.careeros_backend.dto.github.intelligence.facts.RepositoryFact;
import com.careeros.careeros_backend.dto.github.intelligence.signals.EngineeringSignal;
import com.careeros.careeros_backend.dto.github.snapshot.RepositorySnapshot;
import com.careeros.careeros_backend.service.github.intelligence.capabilities.CapabilityBuilder;
import com.careeros.careeros_backend.service.github.intelligence.facts.RepositoryFactExtractor;
import com.careeros.careeros_backend.service.github.intelligence.signals.EngineeringSignalBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepositoryIntelligenceEngineImpl
        implements RepositoryIntelligenceEngine {

    private final RepositoryFactExtractor factExtractor;

    private final EngineeringSignalBuilder signalBuilder;

    private final CapabilityBuilder capabilityBuilder;

    @Override
    public RepositoryIntelligence build(
            RepositorySnapshot snapshot
    ) {

        List<RepositoryFact> facts =
                factExtractor.extract(snapshot);

        List<EngineeringSignal> signals =
                signalBuilder.build(facts);

        List<EngineeringCapability> capabilities =
                capabilityBuilder.build(signals);

        return RepositoryIntelligence.builder()
                .facts(facts)
                .signals(signals)
                .capabilities(capabilities)
                .build();
    }
}