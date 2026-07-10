package com.careeros.careeros_backend.dto.github;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryFingerprint {

    /**
     * Repository owner
     */
    private String owner;

    /**
     * Repository name
     */
    private String repositoryName;

    /**
     * Default branch
     */
    private String defaultBranch;

    /**
     * Latest commit SHA
     */
    private String latestCommitSha;

    /**
     * Last push timestamp
     */
    private String lastPush;

    /**
     * Repository size
     */
    private Integer repositorySize;

    /**
     * Stars
     */
    private Integer stars;

    /**
     * Forks
     */
    private Integer forks;

    /**
     * Last updated timestamp
     */
    private String updatedAt;

    private String fingerprint;

}
