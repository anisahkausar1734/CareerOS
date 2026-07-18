package com.careeros.careeros_backend.dto;

import lombok.Data;

@Data
public class CreateProjectRequest {

    private String email;

    private String githubUrl;

    private String liveUrl;

}