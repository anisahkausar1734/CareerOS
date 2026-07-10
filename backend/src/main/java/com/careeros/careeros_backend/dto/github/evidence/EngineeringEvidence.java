package com.careeros.careeros_backend.dto.github.evidence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EngineeringEvidence {

    /*
     * Programming Languages
     * Example:
     * Java -> 85000
     * JavaScript -> 15000
     */
    private Map<String, Integer> languages;

    /*
     * Complete Repository Structure
     * List of all repository paths.
     */
    private List<String> repositoryTree;

    /*
     * Engineering Evidence Categories
     */

    private Map<String, String> documentation;

    private Map<String, String> buildFiles;

    private Map<String, String> dependencyFiles;

    private Map<String, String> configurationFiles;

    private Map<String, String> deploymentFiles;

    private Map<String, String> workflowFiles;

    private Map<String, String> apiFiles;

    private Map<String, String> environmentFiles;

    /*
     * Repository Activity
     */

    private List<Map<String, Object>> commits;

    private List<Map<String, Object>> contributors;

    /*
     * Repository Statistics
     */

    private Integer stars;

    private Integer forks;

    private Integer watchers;

    private Integer openIssues;

    private Integer releases;

    /*
     * Documentation
     */

    private String readme;

    /*
     * Future-proof custom evidence.
     *
     * Any future collector (Database,
     * Infrastructure, Monitoring,
     * Security, Testing, Cloud, etc.)
     * can add evidence here without
     * changing the DTO.
     */
    @Builder.Default
    private Map<String, Map<String, String>> additionalEvidence =
            new LinkedHashMap<>();

}