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
public class TestingFactProvider
        implements RepositoryFactProvider {

    @Override
    public List<RepositoryFact> extract(
            RepositorySnapshot snapshot
    ) {

        List<RepositoryFact> facts =
                new ArrayList<>();

        List<String> files =
                snapshot.getRepositoryTree();

        if (files == null) {
            return facts;
        }

        for (String file : files) {

            String lower =
                    file.toLowerCase();

            /*
             * Test Directories
             */

            if (lower.contains("/test/")
                    || lower.startsWith("test/")
                    || lower.contains("/tests/")
                    || lower.startsWith("tests/")) {

                facts.add(
                        fact(
                                "Testing",
                                "Test Directory",
                                file
                        )
                );
            }

            /*
             * JUnit
             */

            if (lower.contains("junit")) {

                facts.add(
                        fact(
                                "Testing Framework",
                                "JUnit",
                                file
                        )
                );
            }

            /*
             * Mockito
             */

            if (lower.contains("mockito")) {

                facts.add(
                        fact(
                                "Testing Framework",
                                "Mockito",
                                file
                        )
                );
            }

            /*
             * Jest
             */

            if (lower.contains("jest")) {

                facts.add(
                        fact(
                                "Testing Framework",
                                "Jest",
                                file
                        )
                );
            }

            /*
             * PyTest
             */

            if (lower.contains("pytest")) {

                facts.add(
                        fact(
                                "Testing Framework",
                                "PyTest",
                                file
                        )
                );
            }

            /*
             * Cypress
             */

            if (lower.contains("cypress")) {

                facts.add(
                        fact(
                                "Testing Framework",
                                "Cypress",
                                file
                        )
                );
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

                .category(FactCategory.TESTING)

                .confidence(100)

                .build();

    }

}