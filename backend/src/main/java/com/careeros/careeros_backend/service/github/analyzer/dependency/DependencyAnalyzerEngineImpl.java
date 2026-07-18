package com.careeros.careeros_backend.service.github.analyzer.dependency;

import com.careeros.careeros_backend.dto.github.dependency.DependencyDescriptor;
import com.careeros.careeros_backend.dto.github.source.RepositorySourceFile;
import com.careeros.careeros_backend.dto.github.technology.DetectedTechnology;
import com.careeros.careeros_backend.service.github.parser.DependencyParser;
import com.careeros.careeros_backend.service.github.technology.TechnologyRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DependencyAnalyzerEngineImpl
        implements DependencyAnalyzerEngine {

    private final List<DependencyParser> parsers;

    private final TechnologyRegistry technologyRegistry;

    @Override
    public List<DetectedTechnology> analyze(
            List<RepositorySourceFile> files
    ) {

        List<DetectedTechnology> technologies =
                new ArrayList<>();

        for (RepositorySourceFile file : files) {

            DependencyParser parser =
                    parsers.stream()
                            .filter(p -> p.supports(file))
                            .findFirst()
                            .orElse(null);

            if (parser == null) {
                continue;
            }

            List<DependencyDescriptor> dependencies =
                    parser.parse(file);


                    System.out.println("===== Dependencies =====");

dependencies.forEach(System.out::println);

            for (DependencyDescriptor dependency : dependencies) {

                technologyRegistry
                        .resolve(dependency)

                        
                        .ifPresent(technology ->

                                technologies.add(

                                        DetectedTechnology.builder()

                                                .technology(
                                                        technology
                                                )

                                                .dependency(
                                                        dependency
                                                )

                                                .build()

                                )

                        );

                        System.out.println("===== Technologies =====");

technologies.forEach(System.out::println);

            }

        }

        return technologies;
        

    }

    

}