package com.careeros.careeros_backend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "github")
public class GithubConfig {

    /**
     * GitHub REST API Base URL
     * Example:
     * https://api.github.com
     */
    private String apiBase;

    /**
     * GitHub GraphQL Endpoint
     * Example:
     * https://api.github.com/graphql
     */
    private String graphqlUrl;

    /**
     * GitHub Personal Access Token
     */
    private String token;

}