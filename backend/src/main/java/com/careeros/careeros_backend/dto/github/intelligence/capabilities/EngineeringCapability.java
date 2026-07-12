package com.careeros.careeros_backend.dto.github.intelligence.capabilities;

import com.careeros.careeros_backend.dto.github.intelligence.enums.CapabilityCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EngineeringCapability {

    /*
     * Capability
     * Example:
     * Backend Development
     * REST API Development
     * Database Design
     */
    private String capability;

    /*
     * Capability category
     */
private CapabilityCategory category;
    /*
     * Estimated proficiency
     */
    private Integer proficiency;

    /*
     * Supporting engineering signal
     */
    private String derivedFrom;

    /*
     * Confidence
     */
    private Integer confidence;

}