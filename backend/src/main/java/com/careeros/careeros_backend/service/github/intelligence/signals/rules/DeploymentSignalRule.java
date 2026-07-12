package com.careeros.careeros_backend.service.github.intelligence.signals.rules;

import com.careeros.careeros_backend.dto.github.intelligence.enums.FactCategory;
import com.careeros.careeros_backend.dto.github.intelligence.enums.SignalCategory;
import com.careeros.careeros_backend.dto.github.intelligence.facts.RepositoryFact;
import com.careeros.careeros_backend.dto.github.intelligence.signals.EngineeringSignal;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeploymentSignalRule
        implements SignalRule {

    @Override
    public EngineeringSignal evaluate(
            List<RepositoryFact> facts
    ) {

        long count = facts.stream()
                .filter(f -> f.getCategory() == FactCategory.DEPLOYMENT)
                .count();

        if (count == 0)
            return null;

        int importance = Math.min(100, (int) count * 30);

        return EngineeringSignal.builder()
                .category(SignalCategory.DEVOPS)
                .signal("Deployment Infrastructure")
                .reasoning(count + " deployment technologies detected.")
                .evidence(count + " deployment facts")
                .importance(importance)
                .confidence(100)
                .build();
    }

}