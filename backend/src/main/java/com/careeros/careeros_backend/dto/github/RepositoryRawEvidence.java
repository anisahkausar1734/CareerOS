package com.careeros.careeros_backend.dto.github;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryRawEvidence {

    private Map<String, Object> graphQLResponse;

    private List<Map<String, Object>> repositoryTree;

    private List<Map<String, Object>> rootContents;

    private Map<String, String> importantFiles;

    private String readme;

}