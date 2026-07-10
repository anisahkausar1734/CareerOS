package com.careeros.careeros_backend.dto.github.evidence;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryDeployment {

    private Boolean hasLiveWebsite;

    private String liveWebsite;

    private Boolean hasGithubPages;

    private Boolean hasDocker;

    private Boolean hasGithubActions;

    private Boolean hasDeploymentWorkflow;

    private Boolean hasEnvironmentConfiguration;

}