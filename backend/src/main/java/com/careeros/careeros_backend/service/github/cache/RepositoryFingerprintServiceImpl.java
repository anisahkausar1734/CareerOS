package com.careeros.careeros_backend.service.github.cache;

import com.careeros.careeros_backend.dto.github.RepositoryFingerprint;
import com.careeros.careeros_backend.service.github.GithubGraphQLService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RepositoryFingerprintServiceImpl
        implements RepositoryFingerprintService {

    private final GithubGraphQLService
            githubGraphQLService;

    @Override
    @SuppressWarnings("unchecked")
    public RepositoryFingerprint getFingerprint(
            String githubUrl
    ) {

        String cleaned = githubUrl
                .replace("https://github.com/", "")
                .replace("http://github.com/", "")
                .replaceAll("/$", "");

        String[] parts = cleaned.split("/");

        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid GitHub URL.");
        }

        String owner = parts[0];
        String repository = parts[1];

        Map<String, Object> response =
                githubGraphQLService.getRepositoryOverview(
                        owner,
                        repository
                );

        Map<String, Object> data =
                (Map<String, Object>) response.get("data");

        Map<String, Object> repo =
                (Map<String, Object>) data.get("repository");

        Map<String, Object> defaultBranch =
                (Map<String, Object>) repo.get("defaultBranchRef");

        Map<String, Object> target =
                defaultBranch == null
                        ? null
                        : (Map<String, Object>) defaultBranch.get("target");

        return RepositoryFingerprint.builder()

                .owner(owner)

                .repositoryName(repository)

                .defaultBranch(
                        defaultBranch == null
                                ? ""
                                : (String) defaultBranch.get("name")
                )

                .latestCommitSha(
                        target == null
                                ? ""
                                : (String) target.get("oid")
                )

                .lastPush(
                        (String) repo.get("pushedAt")
                )

                .updatedAt(
                        (String) repo.get("updatedAt")
                )

                .repositorySize(
                        (Integer) repo.get("diskUsage")
                )

                .stars(
                        (Integer) repo.get("stargazerCount")
                )

                .forks(
                        (Integer) repo.get("forkCount")
                )

                .build();

    }

}