package com.careeros.careeros_backend.service.github;

import java.util.Map;

public interface GithubGraphQLService {

    Map<String, Object> getRepositoryOverview(
            String owner,
            String repository
    );

}