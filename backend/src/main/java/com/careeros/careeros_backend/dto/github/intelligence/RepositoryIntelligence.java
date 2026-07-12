package com.careeros.careeros_backend.dto.github.intelligence;

import com.careeros.careeros_backend.dto.github.intelligence.capabilities.EngineeringCapability;
import com.careeros.careeros_backend.dto.github.intelligence.facts.RepositoryFact;
import com.careeros.careeros_backend.dto.github.intelligence.signals.EngineeringSignal;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryIntelligence {

    private List<RepositoryFact> facts;

    private List<EngineeringSignal> signals;

    private List<EngineeringCapability> capabilities;

}