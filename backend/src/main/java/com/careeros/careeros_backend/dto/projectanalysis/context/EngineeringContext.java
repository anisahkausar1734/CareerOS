package com.careeros.careeros_backend.dto.projectanalysis.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EngineeringContext {

    /*
     * Repository Identity
     */
    private String repositoryName;
    private String description;
    private String repositoryUrl;

    /*
     * Languages
     */
    private Map<String, Integer> languages;

    /*
     * Repository Health
     */
    private Integer stars;
    private Integer forks;
    private Integer watchers;
    private Integer contributors;

    /*
     * Documentation
     */
    private Boolean hasReadme;
    private Boolean hasLicense;
    private Boolean hasChangelog;
    private Boolean hasContributingGuide;

    /*
     * Build & Dependency
     */
    private List<String> buildFiles;
    private List<String> dependencyFiles;

    /*
     * Deployment
     */
    private Boolean hasDocker;
    private Boolean hasGithubActions;
    private Boolean hasDeploymentConfiguration;

    /*
     * API
     */
    private Boolean hasOpenApi;

    /*
     * Repository Structure
     */
    private Integer totalFiles;
    private Integer totalDirectories;

    /*
     * Engineering Evidence
     */
    private List<String> importantFiles;

    /*
     * README (truncated if necessary)
     */
    private String readmeSummary;

}