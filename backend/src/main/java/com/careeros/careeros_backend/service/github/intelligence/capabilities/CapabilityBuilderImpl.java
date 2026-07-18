package com.careeros.careeros_backend.service.github.intelligence.capabilities;

import com.careeros.careeros_backend.dto.github.intelligence.capabilities.EngineeringCapability;
import com.careeros.careeros_backend.dto.github.intelligence.signals.EngineeringSignal;
import com.careeros.careeros_backend.service.github.intelligence.capabilities.rules.CapabilityRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CapabilityBuilderImpl
        implements CapabilityBuilder {

    private final List<CapabilityRule> rules;

  @Override
public List<EngineeringCapability> build(
        List<EngineeringSignal> signals
) {

    List<EngineeringCapability> capabilities =

            rules.stream()

                    .map(rule ->
                            rule.evaluate(signals)
                    )

                    .filter(Objects::nonNull)

                    .toList();

    System.out.println("\n========== ENGINEERING CAPABILITIES ==========");

    capabilities.forEach(System.out::println);

    return capabilities;

}

}