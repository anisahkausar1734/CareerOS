package com.careeros.careeros_backend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.careeros.careeros_backend.dto.github.RepositoryEvidenceResponse;
import com.careeros.careeros_backend.dto.github.RepositoryFingerprint;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "repository_evidence_cache")
public class RepositoryEvidenceCache {

    @Id
    private String id;

    private String repositoryUrl;

    private String owner;

    private String repositoryName;

    private RepositoryFingerprint fingerprint;
    // ⭐ New
    private RepositoryEvidenceResponse evidence;

    private String analysisVersion;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime lastCollectedAt;
}