package com.careeros.careeros_backend.service.github.intelligence.signals.rules;

import com.careeros.careeros_backend.dto.github.intelligence.enums.FactCategory;
import com.careeros.careeros_backend.dto.github.intelligence.enums.SignalCategory;
import com.careeros.careeros_backend.dto.github.intelligence.facts.RepositoryFact;
import com.careeros.careeros_backend.dto.github.intelligence.signals.EngineeringSignal;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SecuritySignalRule
        implements SignalRule {

    @Override
    public EngineeringSignal evaluate(
            List<RepositoryFact> facts
    ) {

        long count =
                facts.stream()

                        .filter(f ->
                                f.getCategory() == FactCategory.SECURITY
                        )

                        .count();

        if (count == 0) {
            return null;
        }

        return EngineeringSignal.builder()

                .category(
                        SignalCategory.SECURITY
                )

                .signal(
                        "Security Practices"
                )

                .reasoning(
                        count + " security artifacts detected."
                )

                .evidence(
                        count + " security facts"
                )

                .importance(
                        Math.min(100, (int) count * 30)
                )

                .confidence(100)

                .build();

    }

}