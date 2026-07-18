package com.careeros.careeros_backend.service.github.parser.gradle;

import com.careeros.careeros_backend.dto.github.dependency.DependencyDescriptor;
import com.careeros.careeros_backend.dto.github.source.RepositorySourceFile;
import com.careeros.careeros_backend.service.github.parser.DependencyParser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class GradleDependencyParser
        implements DependencyParser {

    private static final Pattern DEPENDENCY_PATTERN =
            Pattern.compile(
                    "(implementation|api|compileOnly|runtimeOnly|testImplementation|testRuntimeOnly)\\s*[('\\\"]([^:'\"]+):([^:'\"]+):([^'\"\\)]+)[)'\\\"]"
            );

    @Override
    public boolean supports(
            RepositorySourceFile file
    ) {

        if (file == null) {
            return false;
        }

        String name = file.getFileName();

        return "build.gradle".equalsIgnoreCase(name)
                || "build.gradle.kts".equalsIgnoreCase(name);

    }

    @Override
    public List<DependencyDescriptor> parse(
            RepositorySourceFile file
    ) {

        List<DependencyDescriptor> dependencies =
                new ArrayList<>();

        if (file.getContent() == null) {
            return dependencies;
        }

        Matcher matcher =
                DEPENDENCY_PATTERN.matcher(
                        file.getContent()
                );

        while (matcher.find()) {

            dependencies.add(

                    DependencyDescriptor.builder()

                            .scope(
                                    matcher.group(1)
                            )

                            .groupId(
                                    matcher.group(2)
                            )

                            .artifactId(
                                    matcher.group(3)
                            )

                            .version(
                                    matcher.group(4)
                            )

                            .ecosystem(
                                    "GRADLE"
                            )

                            .build()

            );

        }

        return dependencies;

    }

}