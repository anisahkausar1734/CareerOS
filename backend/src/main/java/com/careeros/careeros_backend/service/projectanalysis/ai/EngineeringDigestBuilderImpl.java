package com.careeros.careeros_backend.service.projectanalysis.ai;

import com.careeros.careeros_backend.dto.projectanalysis.context.EngineeringContext;
import org.springframework.stereotype.Service;

@Service
public class EngineeringDigestBuilderImpl
        implements EngineeringDigestBuilder {

    @Override
    public String buildDigest(
            EngineeringContext context
    ) {

        StringBuilder digest =
                new StringBuilder();

        digest.append("Repository Name: ")
                .append(context.getRepositoryName())
                .append("\n\n");

        digest.append("Description: ")
                .append(context.getDescription())
                .append("\n\n");

        digest.append("Languages: ")
                .append(context.getLanguages())
                .append("\n\n");

        digest.append("Repository Statistics\n")
                .append("Stars: ").append(context.getStars()).append("\n")
                .append("Forks: ").append(context.getForks()).append("\n")
                .append("Watchers: ").append(context.getWatchers()).append("\n")
                .append("Contributors: ").append(context.getContributors()).append("\n\n");

        digest.append("Documentation\n")
                .append("README: ").append(context.getHasReadme()).append("\n")
                .append("License: ").append(context.getHasLicense()).append("\n")
                .append("Changelog: ").append(context.getHasChangelog()).append("\n")
                .append("Contributing Guide: ").append(context.getHasContributingGuide()).append("\n\n");

        digest.append("Engineering\n")
                .append("Build Files: ").append(context.getBuildFiles()).append("\n")
                .append("Dependency Files: ").append(context.getDependencyFiles()).append("\n")
                .append("Docker: ").append(context.getHasDocker()).append("\n")
                .append("GitHub Actions: ").append(context.getHasGithubActions()).append("\n")
                .append("Deployment Config: ").append(context.getHasDeploymentConfiguration()).append("\n")
                .append("OpenAPI: ").append(context.getHasOpenApi()).append("\n\n");

        digest.append("Repository Size\n")
                .append("Files: ").append(context.getTotalFiles()).append("\n")
                .append("Directories: ").append(context.getTotalDirectories()).append("\n\n");

        digest.append("Important Files\n")
                .append(context.getImportantFiles())
                .append("\n\n");

        digest.append("README Summary\n")
                .append(context.getReadmeSummary());

        return digest.toString();

    }

}