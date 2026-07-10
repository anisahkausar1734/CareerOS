package com.careeros.careeros_backend.dto.github.evidence;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryHealth {

    private Integer stars;

    private Integer forks;

    private Integer watchers;

    private Integer openIssues;

    private Integer contributors;

    private Integer releases;

    private Integer branches;

    private Integer repositorySize;

    private String latestCommitSha;

    private String latestCommitDate;

    private Boolean activelyMaintained;

}