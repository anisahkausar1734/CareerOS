package com.careeros.careeros_backend.dto.github.evidence;

import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryActivity {

    private Integer totalCommits;

    private Integer totalContributors;

    private List<Map<String,Object>> recentCommits;

    private List<Map<String,Object>> contributors;

}