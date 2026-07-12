package com.careeros.careeros_backend.service.github.intelligence.signals.rules;

import com.careeros.careeros_backend.dto.github.intelligence.enums.FactCategory;
import com.careeros.careeros_backend.dto.github.intelligence.enums.SignalCategory;
import com.careeros.careeros_backend.dto.github.intelligence.facts.RepositoryFact;
import com.careeros.careeros_backend.dto.github.intelligence.signals.EngineeringSignal;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestingSignalRule
        implements SignalRule {

    @Override
    public EngineeringSignal evaluate(
            List<RepositoryFact> facts
    ) {

        long count = facts.stream()
                .filter(f -> f.getCategory() == FactCategory.TESTING)
                .count();

        if (count == 0)
            return null;

        int importance = Math.min(100, (int) count * 25);

        return EngineeringSignal.builder()
                .category(SignalCategory.TESTING)
                .signal("Testing Maturity")
                .reasoning(count + " testing artifacts detected.")
                .evidence(count + " testing facts")
                .importance(importance)
                .confidence(100)
                .build();
    }

}