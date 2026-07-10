package com.careeros.careeros_backend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "repository_evidence")
public class RepositoryEvidence {

    @Id
    private String id;

    /*
     * Repository Identity
     */
    private String repositoryUrl;

    private String owner;

    private String repositoryName;

    private String defaultBranch;

    /*
     * Cache Validation
     */
    private String latestCommitSha;

    /*
     * Entire Repository Evidence
     */
    private String rawGraphQLJson;

private String rawRepositoryTreeJson;

private String normalizedEvidenceJson;

    /*
     * Analysis Version
     */
    private String analysisVersion;

    /*
     * Audit
     */
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime lastCollectedAt;

    private String repositoryFingerprint;

}