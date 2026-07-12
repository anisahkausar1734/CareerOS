package com.careeros.careeros_backend.service.projectanalysis.ai;

import com.careeros.careeros_backend.dto.projectanalysis.context.EngineeringContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EngineeringPromptBuilderImpl
        implements EngineeringPromptBuilder {

@Override
public String buildPrompt(
        EngineeringContext context
) {

    StringBuilder prompt =
            new StringBuilder();

    prompt.append("""
You are a Senior Staff Software Engineer, Engineering Manager and Technical Hiring Committee member.

Your job is to perform a COMPLETE engineering review of this GitHub repository.

You MUST evaluate ONLY using the supplied repository evidence.

Never invent technologies.

Never assume missing files exist.

Never reward technologies alone.

Reward engineering quality.

Return ONLY valid JSON.

Do not use markdown.

Do not wrap the JSON in ```.

""");

    prompt.append(EngineeringRubric.getRubric());

    prompt.append("\n");

    prompt.append(EngineeringJsonSchema.schema());

    prompt.append("\n\n");

    prompt.append("============== REPOSITORY ==============\n");

    prompt.append("Repository Name: ")
            .append(context.getRepositoryName())
            .append("\n");

    prompt.append("Description: ")
            .append(context.getDescription())
            .append("\n");

    prompt.append("Repository URL: ")
            .append(context.getRepositoryUrl())
            .append("\n\n");

    prompt.append("============== LANGUAGES ==============\n");

    prompt.append(context.getLanguages());

    prompt.append("\n\n");

    prompt.append("============== ENGINEERING FACTS ==============\n");

    if (context.getFacts() != null) {

        context.getFacts().forEach(fact ->

                prompt.append("- ")

                        .append(fact.getCategory())

                        .append(" : ")

                        .append(fact.getKey())

                        .append(" = ")

                        .append(fact.getValue())

                        .append("\n")

        );

    }

    prompt.append("\n");

    prompt.append("============== ENGINEERING SIGNALS ==============\n");

    if (context.getSignals() != null) {

        context.getSignals().forEach(signal ->

                prompt.append("- ")

                        .append(signal.getSignal())

                        .append(" (Importance ")

                        .append(signal.getImportance())

                        .append(")\n")

        );

    }

    prompt.append("\n");

    prompt.append("============== ENGINEERING CAPABILITIES ==============\n");

    if (context.getCapabilities() != null) {

        context.getCapabilities().forEach(capability ->

                prompt.append("- ")

                        .append(capability.getCapability())

                        .append(" (Level ")

                        .append(capability.getProficiency())

                        .append(")\n")

        );

    }

    prompt.append("\n");

    prompt.append("============== RAW ENGINEERING DATA ==============\n");

    prompt.append("Stars: ")
            .append(context.getStars())
            .append("\n");

    prompt.append("Forks: ")
            .append(context.getForks())
            .append("\n");

    prompt.append("Contributors: ")
            .append(context.getContributors())
            .append("\n");

    prompt.append("Has README: ")
            .append(context.getHasReadme())
            .append("\n");

    prompt.append("Has Docker: ")
            .append(context.getHasDocker())
            .append("\n");

    prompt.append("Has GitHub Actions: ")
            .append(context.getHasGithubActions())
            .append("\n");

    prompt.append("Has OpenAPI: ")
            .append(context.getHasOpenApi())
            .append("\n");

    prompt.append("Build Files: ")
            .append(context.getBuildFiles())
            .append("\n");

    prompt.append("Dependency Files: ")
            .append(context.getDependencyFiles())
            .append("\n");

    prompt.append("Important Files: ")
            .append(context.getImportantFiles())
            .append("\n");

    prompt.append("\n");

    prompt.append("README:\n");

    prompt.append(context.getReadmeSummary());

    return prompt.toString();

}

}