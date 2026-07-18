package com.careeros.careeros_backend.service.github.intelligence;

import com.careeros.careeros_backend.dto.github.intelligence.RepositoryIntelligence;
import com.careeros.careeros_backend.dto.github.intelligence.capabilities.EngineeringCapability;
import com.careeros.careeros_backend.dto.github.intelligence.facts.RepositoryFact;
import com.careeros.careeros_backend.dto.github.intelligence.signals.EngineeringSignal;
import com.careeros.careeros_backend.dto.github.snapshot.RepositorySnapshot;
import com.careeros.careeros_backend.dto.github.source.RepositorySourceFile;
import com.careeros.careeros_backend.dto.github.technology.DetectedTechnology;
import com.careeros.careeros_backend.service.github.analyzer.dependency.DependencyAnalyzerEngine;
import com.careeros.careeros_backend.service.github.intelligence.capabilities.CapabilityBuilder;
import com.careeros.careeros_backend.service.github.intelligence.facts.RepositoryFactExtractor;
import com.careeros.careeros_backend.service.github.intelligence.facts.TechnologyFactBuilder;
import com.careeros.careeros_backend.service.github.intelligence.signals.EngineeringSignalBuilder;
import com.careeros.careeros_backend.service.github.source.RepositorySourceBuilder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RepositoryIntelligenceEngineImpl
        implements RepositoryIntelligenceEngine {

    private final RepositoryFactExtractor factExtractor;

    private final EngineeringSignalBuilder signalBuilder;

    private final CapabilityBuilder capabilityBuilder;

    private final RepositorySourceBuilder sourceBuilder;

private final DependencyAnalyzerEngine dependencyAnalyzerEngine;

private final TechnologyFactBuilder technologyFactBuilder;

@Override
public RepositoryIntelligence build(
        RepositorySnapshot snapshot
)

{

         try {
    // Build repository source files
    List<RepositorySourceFile> sourceFiles =
            sourceBuilder.build(snapshot);

            System.out.println("\n========== SOURCE FILE COUNT ==========");
System.out.println(sourceFiles.size());

    // Analyze dependencies
    List<DetectedTechnology> technologies =
            dependencyAnalyzerEngine.analyze(sourceFiles);

            System.out.println("\n========== DETECTED TECHNOLOGIES ==========");

technologies.forEach(System.out::println);

    // Convert technologies -> facts
    List<RepositoryFact> dependencyFacts =
            technologyFactBuilder.build(technologies);

    // Collect repository facts
    List<RepositoryFact> facts =
            new ArrayList<>();

    facts.addAll(
            factExtractor.extract(snapshot)
    );

    facts.addAll(
            dependencyFacts
    );

    // Build signals
    List<EngineeringSignal> signals =
            signalBuilder.build(facts);

    // Build capabilities
    List<EngineeringCapability> capabilities =
            capabilityBuilder.build(signals);

System.out.println("\n========== FINAL INTELLIGENCE ==========");
System.out.println("Facts: " + facts.size());
facts.forEach(System.out::println);

System.out.println("\nSignals: " + signals.size());
signals.forEach(System.out::println);

System.out.println("\nCapabilities: " + capabilities.size());
capabilities.forEach(System.out::println);

System.out.println("\n========== FINAL INTELLIGENCE ==========");

System.out.println("Facts = " + facts.size());

System.out.println("Signals = " + signals.size());

System.out.println("Capabilities = " + capabilities.size());

    return RepositoryIntelligence.builder()
            .facts(facts)
            .signals(signals)
            .capabilities(capabilities)
            .build();

   }

    catch (Exception e) {

        System.out.println("========== SNAPSHOT ERROR ==========");
        e.printStackTrace();

        throw e;

    }

}
        }