package com.careeros.careeros_backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GithubInspectionResponse {

    private String repositoryName;

    private String owner;

    private String primaryLanguage;

    private Integer stars;

    private Integer forks;

    private Integer openIssues;

    private Boolean hasWiki;

    private Boolean hasProjects;

    private String defaultBranch;

    private String repositoryUrl;

    private String description;

    private Boolean hasReadme;

    private Boolean hasDocker;

    private Boolean hasCICD;

    private Boolean hasFrontend;

    private Boolean hasBackend;

    private Boolean hasDatabase;

    private Boolean hasAuthentication;

    private Integer controllerCount;

    private Integer serviceCount;

    private Integer repositoryCount;

    private Integer componentCount;

    private Integer configCount;

    private Integer pageCount;

private Boolean hasSecurity;

private Boolean hasJwt;

private Integer dtoCount;

private Integer entityCount;

private Integer modelCount;

private Integer securityCount;

private Integer jwtCount;

private Boolean hasTesting;

private Boolean hasDeployment;

private Boolean hasDocumentation;

private Integer frontendFileCount;

private Integer backendFileCount;

private Integer databaseFileCount;

private Integer authFileCount;

private Integer testFileCount;

private Integer deploymentFileCount;

}