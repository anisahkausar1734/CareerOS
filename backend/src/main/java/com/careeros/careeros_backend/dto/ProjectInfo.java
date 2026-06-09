package com.careeros.careeros_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectInfo {

    private String projectName;

    private boolean hasFrontend;

    private boolean hasBackend;

    private boolean hasDatabase;

    private boolean hasAuthentication;

    private boolean deployed;

    private boolean usesAI;

    private String complexityLevel;

private Integer estimatedUsers;

private Integer teamSize;

private boolean usesMicroservices;
private boolean usesCloud;
private boolean hasCICD;
private boolean openSource;
private boolean researchBased;
private Double revenueGenerated;
private String architectureType;
}