package com.careeros.careeros_backend.dto.github.intelligence.facts;

import com.careeros.careeros_backend.dto.github.intelligence.enums.FactCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryFact {

    /*
     * Unique fact identifier
     */
    private String key;

    /*
     * Fact value
     */
    private String value;

    /*
     * Where this fact came from
     * Example:
     * pom.xml
     * Dockerfile
     * README.md
     */
    private String source;

    /*
     * Fact category
     * BUILD
     * DOCUMENTATION
     * DEPENDENCY
     * DEPLOYMENT
     * TESTING
     * STRUCTURE
     * CONFIGURATION
     */
    private FactCategory category;
    /*
     * Confidence (0-100)
     */
    private Integer confidence;

}