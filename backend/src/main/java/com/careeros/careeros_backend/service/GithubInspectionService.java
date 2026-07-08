package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.GithubInspectionResponse;

public interface GithubInspectionService {

    GithubInspectionResponse inspectRepository(
            String githubUrl
    );
}