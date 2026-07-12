package com.careeros.careeros_backend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.careeros.careeros_backend.dto.github.RepositoryFingerprint;
import com.careeros.careeros_backend.dto.projectanalysis.ProjectCareerImpact;
import com.careeros.careeros_backend.dto.projectanalysis.ProjectEngineeringAnalysis;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "projects")
public class Project {

    @Id
    private String id;

    private String email;

    private String projectName;

    private String description;

    private List<String> techStack;

    private String githubUrl;

    private String liveUrl;

    private ProjectEngineeringAnalysis engineeringAnalysis;

private ProjectCareerImpact careerImpact;

private RepositoryFingerprint repositoryFingerprint;

private LocalDateTime analyzedAt;

private String analysisVersion;

private Integer repositoryQualityScore;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}