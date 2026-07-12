package com.careeros.careeros_backend.dto.github.snapshot;

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
public class RepositorySnapshot {

    /*
     * Repository Identity
     */
    private String owner;

    private String repositoryName;

    private String repositoryUrl;

    private String defaultBranch;

    /*
     * Repository Metadata
     */
    private String description;

    private String homepage;

    private List<String> topics;

    /*
     * Languages (raw bytes from GitHub)
     */
    private Map<String, Integer> languages;

    /*
     * Raw Repository Tree
     */
    private List<String> repositoryTree;

    /*
     * Root Files
     */
    private List<String> rootFiles;

    /*
     * Important Files
     */
    private Map<String, String> importantFiles;

    /*
     * Documentation
     */
    private String readme;

    private String license;

    /*
     * Build Files
     */
    private List<String> buildFiles;

    /*
     * Dependency Files
     */
    private List<String> dependencyFiles;

    /*
     * Configuration Files
     */
    private List<String> configurationFiles;

    /*
     * Workflow Files
     */
    private List<String> workflowFiles;

    /*
     * Deployment Files
     */
    private List<String> deploymentFiles;

    /*
     * Test Files
     */
    private List<String> testFiles;

    /*
     * Statistics
     */
    private Integer stars;

    private Integer forks;

    private Integer watchers;

    private Integer contributors;

    private Integer totalFiles;

    private Integer totalDirectories;

}