package com.careeros.careeros_backend.service.github.intelligence.facts.providers;

import com.careeros.careeros_backend.dto.github.intelligence.enums.FactCategory;
import com.careeros.careeros_backend.dto.github.intelligence.facts.RepositoryFact;
import com.careeros.careeros_backend.dto.github.snapshot.RepositorySnapshot;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityFactProvider
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

            String lower = file.toLowerCase();

            if (lower.contains("security")) {

                facts.add(fact(
                        "Security Policy",
                        "Present",
                        file
                ));

            }

            if (lower.contains(".env.example")) {

                facts.add(fact(
                        "Environment Configuration",
                        "Example File",
                        file
                ));

            }

            if (lower.contains("application.yml")
                    || lower.contains("application.properties")) {

                facts.add(fact(
                        "Application Configuration",
                        "Spring Configuration",
                        file
                ));

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

                .category(FactCategory.SECURITY)

                .confidence(100)

                .build();

    }

}