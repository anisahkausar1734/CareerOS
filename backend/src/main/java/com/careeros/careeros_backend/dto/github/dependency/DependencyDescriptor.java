package com.careeros.careeros_backend.dto.github.dependency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DependencyDescriptor {

    /*
     * Maven/Gradle/NPM Group
     */
    private String groupId;

    /*
     * Artifact / Package
     */
    private String artifactId;

    /*
     * Dependency Version
     */
    private String version;

    /*
     * compile/test/runtime...
     */
    private String scope;

    /*
     * maven / npm / pip / cargo / gomod
     */
    private String ecosystem;

}