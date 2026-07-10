package com.careeros.careeros_backend.service.github.evidence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EngineeringEvidenceBuilderImpl
        implements EngineeringEvidenceBuilder {

    @Override
    public EngineeringEvidenceBundle build(
            Map<String, String> importantFiles
    ) {

        Map<String,String> documentation =
                new LinkedHashMap<>();

        Map<String,String> build =
                new LinkedHashMap<>();

        Map<String,String> dependency =
                new LinkedHashMap<>();

        Map<String,String> configuration =
                new LinkedHashMap<>();

        Map<String,String> deployment =
                new LinkedHashMap<>();

        Map<String,String> workflow =
                new LinkedHashMap<>();

        Map<String,String> api =
                new LinkedHashMap<>();

        Map<String,String> environment =
                new LinkedHashMap<>();

        for (Map.Entry<String,String> entry : importantFiles.entrySet()) {

            String path = entry.getKey();
            String content = entry.getValue();

            classify(
                    path,
                    content,
                    documentation,
                    build,
                    dependency,
                    configuration,
                    deployment,
                    workflow,
                    api,
                    environment
            );

        }

        return EngineeringEvidenceBundle.builder()
                .documentation(documentation)
                .buildFiles(build)
                .dependencyFiles(dependency)
                .configurationFiles(configuration)
                .deploymentFiles(deployment)
                .workflowFiles(workflow)
                .apiFiles(api)
                .environmentFiles(environment)
                .build();

    }

    private void classify(

            String path,

            String content,

            Map<String,String> documentation,

            Map<String,String> build,

            Map<String,String> dependency,

            Map<String,String> configuration,

            Map<String,String> deployment,

            Map<String,String> workflow,

            Map<String,String> api,

            Map<String,String> environment

    ) {

        String lower =
                path.toLowerCase(Locale.ROOT);

        if (lower.contains("readme")
                || lower.contains("license")
                || lower.contains("contributing")
                || lower.contains("changelog")) {

            documentation.put(path, content);
            return;
        }

        if (lower.contains("workflow")) {

            workflow.put(path, content);
            return;
        }

        if (lower.contains("docker")
                || lower.contains("compose")
                || lower.contains("vercel")
                || lower.contains("render")
                || lower.contains("netlify")) {

            deployment.put(path, content);
            return;
        }

        if (lower.contains("swagger")
                || lower.contains("openapi")) {

            api.put(path, content);
            return;
        }

        if (lower.contains(".env")
                || lower.contains("application")
                || lower.contains("bootstrap")) {

            environment.put(path, content);
            return;
        }

        if (lower.endsWith("pom.xml")
                || lower.endsWith("build.gradle")
                || lower.endsWith("build.gradle.kts")
                || lower.endsWith("package.json")
                || lower.endsWith("cargo.toml")
                || lower.endsWith("go.mod")
                || lower.endsWith("pubspec.yaml")) {

            build.put(path, content);
            return;
        }

        if (lower.endsWith("requirements.txt")
                || lower.endsWith("composer.json")
                || lower.endsWith("poetry.lock")
                || lower.endsWith("pipfile")) {

            dependency.put(path, content);
            return;
        }

        configuration.put(path, content);

    }

}