package com.careeros.careeros_backend.service.github;

import com.careeros.careeros_backend.config.GithubConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class GithubApiClient {

    private final GithubConfig githubConfig;

    private final RestTemplate restTemplate;

    /**
     * Creates authenticated GitHub headers.
     */
    private HttpHeaders createHeaders() {

        HttpHeaders headers = new HttpHeaders();

        headers.setBearerAuth(
                githubConfig.getToken()
        );

        headers.set(
                HttpHeaders.ACCEPT,
                "application/vnd.github+json"
        );

        headers.set(
                "X-GitHub-Api-Version",
                "2022-11-28"
        );

        return headers;
    }

    /**
     * Generic GET request.
     */
    public <T> T get(
            String endpoint,
            Class<T> responseType
    ) {

        HttpEntity<Void> entity =
                new HttpEntity<>(
                        createHeaders()
                );

        ResponseEntity<T> response =
                restTemplate.exchange(
                        githubConfig.getApiBase() + endpoint,
                        HttpMethod.GET,
                        entity,
                        responseType
                );

        return response.getBody();
    }

    /**
     * Generic GET request for generic collections.
     */
    public <T> T get(
            String endpoint,
            ParameterizedTypeReference<T> responseType
    ) {

        HttpEntity<Void> entity =
                new HttpEntity<>(
                        createHeaders()
                );

        ResponseEntity<T> response =
                restTemplate.exchange(
                        githubConfig.getApiBase() + endpoint,
                        HttpMethod.GET,
                        entity,
                        responseType
                );

        return response.getBody();
    }

    /**
     * Execute a GitHub GraphQL query.
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> graphQL(
            String query
    ) {

        HttpHeaders headers =
                createHeaders();

        headers.setContentType(
                MediaType.APPLICATION_JSON
        );

        HttpEntity<Map<String, String>> entity =
                new HttpEntity<>(
                        Map.of(
                                "query",
                                query
                        ),
                        headers
                );

        ResponseEntity<Map> response =
                restTemplate.exchange(
                        githubConfig.getGraphqlUrl(),
                        HttpMethod.POST,
                        entity,
                        Map.class
                );

        return (Map<String, Object>) response.getBody();
    }

}