package com.careeros.careeros_backend.service.github.intelligence.facts.providers;

import com.careeros.careeros_backend.dto.github.intelligence.enums.FactCategory;
import com.careeros.careeros_backend.dto.github.intelligence.facts.RepositoryFact;
import com.careeros.careeros_backend.dto.github.snapshot.RepositorySnapshot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildFactProvider
        implements RepositoryFactProvider {

    @Override
    public List<RepositoryFact> extract(
            RepositorySnapshot snapshot
    ) {

        List<RepositoryFact> facts =
                new ArrayList<>();

        List<String> buildFiles =
                snapshot.getBuildFiles();

        if (buildFiles == null) {
            return facts;
        }

        for (String file : buildFiles) {

            switch (file.toLowerCase()) {

                case "pom.xml" -> {

                    facts.add(fact(
                            "Build System",
                            "Maven",
                            file
                    ));

                }

                case "build.gradle",
                     "build.gradle.kts" -> {

                    facts.add(fact(
                            "Build System",
                            "Gradle",
                            file
                    ));

                }

                case "package.json" -> {

                    facts.add(fact(
                            "Dependency Manager",
                            "npm",
                            file
                    ));

                }

                case "requirements.txt" -> {

                    facts.add(fact(
                            "Dependency Manager",
                            "pip",
                            file
                    ));

                }

                case "cargo.toml" -> {

                    facts.add(fact(
                            "Dependency Manager",
                            "Cargo",
                            file
                    ));

                }

                case "go.mod" -> {

                    facts.add(fact(
                            "Dependency Manager",
                            "Go Modules",
                            file
                    ));

                }

                case "composer.json" -> {

                    facts.add(fact(
                            "Dependency Manager",
                            "Composer",
                            file
                    ));

                }

            }

        }

        return facts;

    }

    private RepositoryFact fact(

            String key,

            String value,

            String source

    ) {

        return RepositoryFact.builder()

                .key(key)

                .value(value)

                .source(source)

                .category(FactCategory.BUILD)

                .confidence(100)

                .build();

    }

}