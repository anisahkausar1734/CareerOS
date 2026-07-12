package com.careeros.careeros_backend.service.github.intelligence.facts.providers;

import com.careeros.careeros_backend.dto.github.intelligence.enums.FactCategory;
import com.careeros.careeros_backend.dto.github.intelligence.facts.RepositoryFact;
import com.careeros.careeros_backend.dto.github.snapshot.RepositorySnapshot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DocumentationFactProvider
        implements RepositoryFactProvider {

    @Override
    public List<RepositoryFact> extract(
            RepositorySnapshot snapshot
    ) {

        List<RepositoryFact> facts =
                new ArrayList<>();

        Map<String, String> files =
                snapshot.getImportantFiles();

        if (files == null) {
            return facts;
        }

        addIfPresent(facts, files, "README.md", "Documentation", "README");
        addIfPresent(facts, files, "LICENSE", "License", "LICENSE");
        addIfPresent(facts, files, "CHANGELOG.md", "Changelog", "CHANGELOG");
        addIfPresent(facts, files, "CONTRIBUTING.md", "Contribution Guide", "CONTRIBUTING");
        addIfPresent(facts, files, "CODE_OF_CONDUCT.md", "Code of Conduct", "CODE_OF_CONDUCT");
        addIfPresent(facts, files, "SECURITY.md", "Security Policy", "SECURITY");

        return facts;

    }

    private void addIfPresent(

            List<RepositoryFact> facts,

            Map<String, String> files,

            String filename,

            String key,

            String value

    ) {

        if (!files.containsKey(filename)) {
            return;
        }

        facts.add(

                RepositoryFact.builder()

                        .key(key)

                        .value(value)

                        .source(filename)

                        .category(FactCategory.DOCUMENTATION)

                        .confidence(100)

                        .build()

        );

    }

}