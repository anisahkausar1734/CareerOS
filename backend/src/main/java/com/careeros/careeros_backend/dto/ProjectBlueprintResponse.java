package com.careeros.careeros_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectBlueprintResponse {

    private String projectName;

    private String difficulty;

    private String duration;

    private List<String> techStack;

    private List<String> features;

    private String careerImpact;

    private String resumeImpact;

    private String internshipImpact;

    private String roadmap;

}