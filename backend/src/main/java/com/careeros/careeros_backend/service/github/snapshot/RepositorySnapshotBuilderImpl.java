package com.careeros.careeros_backend.service.github.snapshot;

import com.careeros.careeros_backend.dto.github.RepositoryRawEvidence;
import com.careeros.careeros_backend.dto.github.snapshot.RepositorySnapshot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@SuppressWarnings("unchecked")
public class RepositorySnapshotBuilderImpl
        implements RepositorySnapshotBuilder {

    @Override
    public RepositorySnapshot build(
            RepositoryRawEvidence raw
    ) {

         try {

        System.out.println("S1");
        Map<String, Object> data =
                (Map<String, Object>) raw.getGraphQLResponse().get("data");

               System.out.println("S2"); 
        Map<String, Object> repository =
                (Map<String, Object>) data.get("repository");

                System.out.println("S3");
        Map<String, Integer> languages =
                extractLanguages(repository);

                System.out.println("S4");
        List<String> repositoryTree =
                raw.getRepositoryTree()
                        .stream()
                        .map(node -> (String) node.get("path"))
                        .toList();

                        System.out.println("S5");

                        System.out.println("S6 - Before Builder");

                        System.out.println("\n========== IMPORTANT FILES ==========");
System.out.println("Count = " + raw.getImportantFiles().size());

raw.getImportantFiles().forEach((path, content) -> {
    System.out.println(path + " -> " +
            (content == null ? 0 : content.length()));
});
                        
RepositorySnapshot snapshot = RepositorySnapshot.builder()
                /*
                 * Identity
                 */
                .owner(
                        extractOwner(repository)
                )

                .repositoryName(
                        (String) repository.get("name")
                )

                .repositoryUrl(
                        (String) repository.get("url")
                )

                .defaultBranch(
                        extractDefaultBranch(repository)
                )

                /*
                 * Metadata
                 */
                .description(
                        (String) repository.get("description")
                )

                .homepage(
                        (String) repository.get("homepageUrl")
                )

                .topics(
                        extractTopics(repository)
                )

                /*
                 * Languages
                 */
                .languages(
                        languages
                )

                /*
                 * Repository Structure
                 */
                .repositoryTree(
                        repositoryTree
                )

               .rootFiles(

    raw.getRootContents() == null

            ? List.of()

            : raw.getRootContents()

                .stream()

                .map(node -> (String) node.get("name"))

                .toList()

)

                .importantFiles(
                        raw.getImportantFiles()
                )

                .readme(
                        raw.getReadme()
                )

                /*
                 * Statistics
                 */
                .stars(
                        intValue(repository.get("stargazerCount"))
                )

                .forks(
                        intValue(repository.get("forkCount"))
                )

                .watchers(
                        extractWatchers(repository)
                )

                .contributors(0)

                .totalFiles(
                        repositoryTree.size()
                )

                .totalDirectories(
                        countDirectories(repositoryTree)
                )

                /*
                 * Build Files
                 */
                .buildFiles(
                        repositoryTree.stream()
                                .filter(this::isBuildFile)
                                .toList()
                )

                /*
                 * Dependency Files
                 */
                .dependencyFiles(
                        repositoryTree.stream()
                                .filter(this::isDependencyFile)
                                .toList()
                )

                /*
                 * Configuration Files
                 */
                .configurationFiles(
                        repositoryTree.stream()
                                .filter(this::isConfigurationFile)
                                .toList()
                )

                /*
                 * Workflow Files
                 */
                .workflowFiles(
                        repositoryTree.stream()
                                .filter(this::isWorkflowFile)
                                .toList()
                )

                /*
                 * Deployment Files
                 */
                .deploymentFiles(
                        repositoryTree.stream()
                                .filter(this::isDeploymentFile)
                                .toList()
                )

                /*
                 * Test Files
                 */
                 .testFiles(
                repositoryTree.stream()
                        .filter(this::isTestFile)
                        .toList()
        )

        .build();


System.out.println("\n========== SNAPSHOT IMPORTANT FILES ==========");
System.out.println(snapshot.getImportantFiles().size());

          System.out.println("\n========== SNAPSHOT ==========");

System.out.println("Important Files: " + snapshot.getImportantFiles().keySet());
System.out.println("Build Files: " + snapshot.getBuildFiles());
System.out.println("Dependency Files: " + snapshot.getDependencyFiles());
System.out.println("Configuration Files: " + snapshot.getConfigurationFiles());
System.out.println("Workflow Files: " + snapshot.getWorkflowFiles());
System.out.println("Deployment Files: " + snapshot.getDeploymentFiles());
System.out.println("README Length: " +
        (snapshot.getReadme() == null ? 0 : snapshot.getReadme().length()));

return snapshot;   

      }

    catch (Exception e) {

        System.out.println("========== SNAPSHOT ERROR ==========");
        e.printStackTrace();

        throw e;

    }

}

    

    private Map<String, Integer> extractLanguages(
            Map<String, Object> repository
    ) {

        Map<String, Object> languages =
                (Map<String, Object>) repository.get("languages");

        if (languages == null) {
            return Map.of();
        }

      List<Map<String, Object>> edges =
        (List<Map<String, Object>>) languages.get("edges");

if (edges == null) {
    return Map.of();
}

return edges.stream()

                .collect(java.util.stream.Collectors.toMap(

                        edge -> ((Map<String, Object>) edge.get("node")).get("name").toString(),

                        edge -> ((Number) edge.get("size")).intValue()

                ));

    }

   @SuppressWarnings("unchecked")
private String extractOwner(
        Map<String, Object> repository
) {

    System.out.println("\n========== REPOSITORY MAP ==========");
    System.out.println(repository);
    System.out.println("====================================");

    Map<String, Object> owner =
            (Map<String, Object>) repository.get("owner");

    System.out.println("Owner = " + owner);

    if (owner == null) {
        return "";
    }

    return String.valueOf(owner.get("login"));

}

    private String extractDefaultBranch(
            Map<String, Object> repository
    ) {

        Map<String, Object> branch =
                (Map<String, Object>) repository.get("defaultBranchRef");

        return branch == null
                ? "main"
                : branch.get("name").toString();

    }

    private List<String> extractTopics(
            Map<String, Object> repository
    ) {

        Map<String, Object> topicData =
                (Map<String, Object>) repository.get("repositoryTopics");

        if (topicData == null) {
            return List.of();
        }

       List<Map<String, Object>> edges =
        (List<Map<String, Object>>) topicData.get("edges");

if (edges == null) {
    return List.of();
}

return edges.stream()

                .map(edge ->

                        ((Map<String, Object>)
                                edge.get("node"))

                                .get("topic")

                )

                .map(topic ->

                        ((Map<String, Object>) topic)

                                .get("name")

                                .toString()

                )

                .toList();

    }

    private Integer extractWatchers(
            Map<String, Object> repository
    ) {

        Map<String, Object> watchers =
                (Map<String, Object>) repository.get("watchers");

        if (watchers == null) {
            return 0;
        }

        return intValue(
                watchers.get("totalCount")
        );

    }

    private Integer intValue(
            Object value
    ) {

        if (value == null) {
            return 0;
        }

        return ((Number) value).intValue();

    }

    private Integer countDirectories(
            List<String> tree
    ) {

        return (int) tree.stream()

                .filter(path -> path.contains("/"))

                .map(path -> path.substring(0, path.lastIndexOf("/")))

                .distinct()

                .count();

    }

    private boolean isBuildFile(String path) {
        return path.endsWith("pom.xml")
                || path.endsWith("build.gradle")
                || path.endsWith("build.gradle.kts");
    }

    private boolean isDependencyFile(String path) {
        return path.endsWith("package.json")
                || path.endsWith("requirements.txt")
                || path.endsWith("go.mod")
                || path.endsWith("Cargo.toml");
    }

    private boolean isConfigurationFile(String path) {
        return path.contains("application.yml")
                || path.contains("application.yaml")
                || path.contains("application.properties")
                || path.endsWith(".env");
    }

    private boolean isWorkflowFile(String path) {
        return path.startsWith(".github/workflows");
    }

    private boolean isDeploymentFile(String path) {
        return path.endsWith("Dockerfile")
                || path.endsWith("docker-compose.yml")
                || path.contains("kubernetes")
                || path.contains("k8s");
    }

    private boolean isTestFile(String path) {
        return path.contains("/test/")
                || path.contains("/tests/")
                || path.endsWith("Test.java")
                || path.endsWith(".spec.ts")
                || path.endsWith(".test.js");
    }

}