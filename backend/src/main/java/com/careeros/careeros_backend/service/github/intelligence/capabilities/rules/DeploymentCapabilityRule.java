package com.careeros.careeros_backend.service.github.intelligence.capabilities.rules;

import com.careeros.careeros_backend.dto.github.intelligence.capabilities.EngineeringCapability;
import com.careeros.careeros_backend.dto.github.intelligence.enums.CapabilityCategory;
import com.careeros.careeros_backend.dto.github.intelligence.enums.SignalCategory;
import com.careeros.careeros_backend.dto.github.intelligence.signals.EngineeringSignal;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeploymentCapabilityRule
        implements CapabilityRule {

    @Override
    public EngineeringCapability evaluate(
            List<EngineeringSignal> signals
    ) {

        EngineeringSignal signal = signals.stream()
                .filter(s -> s.getCategory() == SignalCategory.DEVOPS)
                .findFirst()
                .orElse(null);

        if (signal == null)
            return null;

        return EngineeringCapability.builder()
                .capability("Cloud & DevOps")
                .category(CapabilityCategory.DEVOPS)
                .derivedFrom(signal.getSignal())
                .proficiency(signal.getImportance())
                .confidence(signal.getConfidence())
                .build();
    }

}