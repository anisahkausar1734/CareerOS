package com.careeros.careeros_backend.service.github.intelligence.capabilities.rules;

import com.careeros.careeros_backend.dto.github.intelligence.capabilities.EngineeringCapability;
import com.careeros.careeros_backend.dto.github.intelligence.enums.CapabilityCategory;
import com.careeros.careeros_backend.dto.github.intelligence.enums.SignalCategory;
import com.careeros.careeros_backend.dto.github.intelligence.signals.EngineeringSignal;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SecurityCapabilityRule
        implements CapabilityRule {

    @Override
    public EngineeringCapability evaluate(
            List<EngineeringSignal> signals
    ) {

        EngineeringSignal signal =
                signals.stream()

                        .filter(s ->
                                s.getCategory() == SignalCategory.SECURITY
                        )

                        .findFirst()

                        .orElse(null);

        if (signal == null) {
            return null;
        }

        return EngineeringCapability.builder()

                .capability(
                        "Secure Software Development"
                )

                .category(
                        CapabilityCategory.SECURITY
                )

                .derivedFrom(
                        signal.getSignal()
                )

                .proficiency(
                        signal.getImportance()
                )

                .confidence(
                        signal.getConfidence()
                )

                .build();

    }

}