package com.careeros.careeros_backend.service.github.intelligence.signals.rules;

import com.careeros.careeros_backend.dto.github.intelligence.enums.FactCategory;
import com.careeros.careeros_backend.dto.github.intelligence.enums.SignalCategory;
import com.careeros.careeros_backend.dto.github.intelligence.facts.RepositoryFact;
import com.careeros.careeros_backend.dto.github.intelligence.signals.EngineeringSignal;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentationSignalRule
        implements SignalRule {

    @Override
    public EngineeringSignal evaluate(
            List<RepositoryFact> facts
    ) {

        long documentationFacts =

                facts.stream()

                        .filter(

                                fact -> fact.getCategory()
                                        == FactCategory.DOCUMENTATION

                        )

                        .count();

        if (documentationFacts == 0) {

            return null;

        }

        int importance =

                switch ((int) documentationFacts) {

                    case 1 -> 40;

                    case 2 -> 60;

                    case 3 -> 75;

                    case 4 -> 90;

                    default -> 100;

                };

        return EngineeringSignal.builder()

                .category(
                        SignalCategory.DOCUMENTATION
                )

                .signal(
                        "Documentation Quality"
                )

                .reasoning(
                        documentationFacts +
                        " documentation artifacts detected."
                )

                .evidence(
                        documentationFacts +
                        " documentation files"
                )

                .importance(
                        importance
                )

                .confidence(
                        100
                )

                .build();

    }

}