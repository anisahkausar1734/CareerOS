package com.careeros.careeros_backend.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProjectAnalysisResponse {

    private Integer projectScore;

    private Integer resumeImpact;

    private Integer internshipImpact;

    private Integer jobImpact;

    private String verdict;

    private List<String> strengths;

    private List<String> weaknesses;

    private List<String> improvements;

    private Integer technicalComplexity;

private Integer codeQuality;

private Integer architectureScore;

private Integer deploymentScore;

private Integer roleAlignment;

private Integer industryValue;

private Integer githubScore;

private Integer engineeringQuality;

private Integer productionReadiness;

private String projectName;


}