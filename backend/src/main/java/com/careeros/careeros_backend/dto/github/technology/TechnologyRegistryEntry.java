package com.careeros.careeros_backend.dto.github.technology;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyRegistryEntry {

    /*
     * Exact match
     */
    private String groupId;
    private String artifactId;

    /*
     * Wildcard support
     */
    private String groupPattern;
    private String artifactPattern;

    /*
     * Technology
     */
    private TechnologyDescriptor technology;

}