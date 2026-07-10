package com.careeros.careeros_backend.service.github;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GithubRestService {

    private final GithubApiClient githubApiClient;

    /**
     * Repository metadata
     */
    public Map<String, Object> getRepository(
            String owner,
            String repo
    ) {

        return githubApiClient.get(
                "/repos/" + owner + "/" + repo,
                Map.class
        );
    }

public List<Map<String, Object>> getBranches(
        String owner,
        String repository
) {

    return githubApiClient.get(
            "/repos/"
                    + owner
                    + "/"
                    + repository
                    + "/branches",
            new ParameterizedTypeReference<>() {}
    );

}


public Map<String, Object> getTopics(
        String owner,
        String repository
) {

    return githubApiClient.get(
            "/repos/"
                    + owner
                    + "/"
                    + repository
                    + "/topics",
            Map.class
    );

}


    /**
     * Repository language statistics
     */
    public Map<String, Object> getLanguages(
            String owner,
            String repo
    ) {

        return githubApiClient.get(
                "/repos/" + owner + "/" + repo + "/languages",
                Map.class
        );
    }

    /**
     * Complete repository tree
     */
    public Map<String, Object> getRepositoryTree(
            String owner,
            String repo,
            String branch
    ) {

        return githubApiClient.get(
                "/repos/"
                        + owner
                        + "/"
                        + repo
                        + "/git/trees/"
                        + branch
                        + "?recursive=1",
                Map.class
        );
    }

    /**
     * Root repository contents
     */
    public List<Map<String, Object>> getRootContents(
            String owner,
            String repo
    ) {

        return githubApiClient.get(
                "/repos/"
                        + owner
                        + "/"
                        + repo
                        + "/contents",
                new ParameterizedTypeReference<>() {}
        );
    }

    /**
     * Read any file from repository
     */
    public Map<String, Object> getFile(
            String owner,
            String repo,
            String path
    ) {

        return githubApiClient.get(
                "/repos/"
                        + owner
                        + "/"
                        + repo
                        + "/contents/"
                        + path,
                Map.class
        );
    }

    /**
     * Contributors
     */
    public List<Map<String, Object>> getContributors(
            String owner,
            String repo
    ) {

        return githubApiClient.get(
                "/repos/"
                        + owner
                        + "/"
                        + repo
                        + "/contributors",
                new ParameterizedTypeReference<>() {}
        );
    }

    /**
     * Recent commits
     */
    public List<Map<String, Object>> getCommits(
            String owner,
            String repo
    ) {

        return githubApiClient.get(
                "/repos/"
                        + owner
                        + "/"
                        + repo
                        + "/commits",
                new ParameterizedTypeReference<>() {}
        );
    }

    /**
     * Releases
     */
    public List<Map<String, Object>> getReleases(
            String owner,
            String repo
    ) {

        return githubApiClient.get(
                "/repos/"
                        + owner
                        + "/"
                        + repo
                        + "/releases",
                new ParameterizedTypeReference<>() {}
        );
    }

}