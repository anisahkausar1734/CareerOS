package com.careeros.careeros_backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateProjectRequest {

    private String email;

    private String projectName;

    private String description;

    private List<String> techStack;

    private String githubUrl;

    private String liveUrl;
}