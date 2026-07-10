package com.careeros.careeros_backend.service.github;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class GithubGraphQLServiceImpl
        implements GithubGraphQLService {

    private final GithubApiClient githubApiClient;

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getRepositoryOverview(
            String owner,
            String repository
    ) {

        String query = """
        query {

          repository(
            owner: "%s",
            name: "%s"
          ) {

            name

            description

            homepageUrl

            url

            createdAt

            updatedAt

            pushedAt

            isPrivate

            isArchived

            diskUsage

            stargazerCount

            forkCount

            openGraphImageUrl

            licenseInfo {
              name
            }

            defaultBranchRef {
              name
              target {
                ... on Commit {
                  oid
                  committedDate
                }
              }
            }

            watchers {
              totalCount
            }

            issues(states:OPEN) {
              totalCount
            }

            releases(first:20) {
              totalCount
            }

            refs(
              refPrefix:"refs/heads/",
              first:100
            ) {
              totalCount
            }

            repositoryTopics(first:30) {
              nodes {
                topic {
                  name
                }
              }
            }

            languages(first:20) {
              edges {
                size
                node {
                  name
                }
              }
            }

          }

        }
        """.formatted(owner, repository);

        return githubApiClient.graphQL(query);

    }

}