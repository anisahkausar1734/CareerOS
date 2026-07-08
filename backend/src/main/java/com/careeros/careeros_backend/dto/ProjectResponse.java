package com.careeros.careeros_backend.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProjectResponse {

    private String id;

    private String projectName;

    private String description;

    private List<String> techStack;

    private String githubUrl;

    private String liveUrl;

    private Integer projectScore;

    private Integer technicalComplexity;

private Integer engineeringQuality;

private Integer productionReadiness;

private Integer roleAlignment;

private Integer industryValue;

private Integer codeQuality;

    private Integer resumeImpact;

    private Integer internshipImpact;

    private Integer jobImpact;

    private String verdict;

    private List<String> strengths;

    private List<String> weaknesses;

    private List<String> improvements;
}