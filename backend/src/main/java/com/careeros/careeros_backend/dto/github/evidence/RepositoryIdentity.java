package com.careeros.careeros_backend.dto.github.evidence;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryIdentity {

    private String repositoryName;

    private String owner;

    private String repositoryUrl;

    private String homepage;

    private String description;

    private String defaultBranch;

    private String license;

    private Boolean isPrivate;

    private Boolean isArchived;

    private List<String> topics;

    private List<String> primaryLanguages;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime lastPush;

}