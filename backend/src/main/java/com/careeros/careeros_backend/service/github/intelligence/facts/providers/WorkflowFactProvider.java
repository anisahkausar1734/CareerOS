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
public class WorkflowFactProvider
        implements RepositoryFactProvider {

    @Override
    public List<RepositoryFact> extract(
            RepositorySnapshot snapshot
    ) {

        List<RepositoryFact> facts =
                new ArrayList<>();

        List<String> workflows =
                snapshot.getWorkflowFiles();

        if (workflows == null) {
            return facts;
        }

        for (String workflow : workflows) {

            facts.add(
                    fact(
                            "CI/CD",
                            "Workflow Present",
                            workflow
                    )
            );

            String lower =
                    workflow.toLowerCase();

            if (lower.contains("build")) {

                facts.add(
                        fact(
                                "Automation",
                                "Build Pipeline",
                                workflow
                        )
                );

            }

            if (lower.contains("test")) {

                facts.add(
                        fact(
                                "Automation",
                                "Automated Testing",
                                workflow
                        )
                );

            }

            if (lower.contains("deploy")) {

                facts.add(
                        fact(
                                "Automation",
                                "Deployment Pipeline",
                                workflow
                        )
                );

            }

            if (lower.contains("release")) {

                facts.add(
                        fact(
                                "Automation",
                                "Release Pipeline",
                                workflow
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

                .category(FactCategory.WORKFLOW)

                .confidence(100)

                .build();

    }

}