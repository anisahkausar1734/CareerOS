package com.careeros.careeros_backend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "github.evidence")
public class EngineeringEvidenceProperties {

    /**
     * Key = Category Name
     *
     * Example:
     * documentation
     * deployment
     * workflow
     * build
     * dependency
     * configuration
     * api
     * environment
     * security
     * testing
     * monitoring
     * infrastructure
     */
    private Map<String, EvidenceCategory> categories =
            new LinkedHashMap<>();

    @Data
    public static class EvidenceCategory {

        /**
         * Exact filenames
         *
         * Example:
         * README.md
         * Dockerfile
         * pom.xml
         */
        private List<String> exactNames;

        /**
         * Keywords contained in the file path
         *
         * Example:
         * workflow
         * docker
         * swagger
         */
        private List<String> contains;

        /**
         * File extensions
         *
         * Example:
         * yml
         * yaml
         * json
         * xml
         */
        private List<String> extensions;

        /**
         * Directories to ignore for this category
         */
        private List<String> ignoredDirectories;

    }

}