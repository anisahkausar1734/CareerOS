package com.careeros.careeros_backend.service.github.intelligence.capabilities;

import com.careeros.careeros_backend.dto.github.intelligence.capabilities.EngineeringCapability;
import com.careeros.careeros_backend.dto.github.intelligence.signals.EngineeringSignal;

import java.util.List;

public interface CapabilityBuilder {

    List<EngineeringCapability> build(
            List<EngineeringSignal> signals
    );

}