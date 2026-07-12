package com.careeros.careeros_backend.service.github.intelligence.capabilities.rules;

import com.careeros.careeros_backend.dto.github.intelligence.capabilities.EngineeringCapability;
import com.careeros.careeros_backend.dto.github.intelligence.signals.EngineeringSignal;

import java.util.List;

public interface CapabilityRule {

    EngineeringCapability evaluate(
            List<EngineeringSignal> signals
    );

}