package com.careeros.careeros_backend.service.github.parser.go;

import com.careeros.careeros_backend.dto.github.dependency.DependencyDescriptor;
import com.careeros.careeros_backend.dto.github.source.RepositorySourceFile;
import com.careeros.careeros_backend.service.github.parser.DependencyParser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GoModDependencyParser
        implements DependencyParser {

    @Override
    public boolean supports(
            RepositorySourceFile file
    ) {

        return file != null
                && "go.mod".equalsIgnoreCase(
                file.getFileName()
        );

    }

    @Override
    public List<DependencyDescriptor> parse(
            RepositorySourceFile file
    ) {

        List<DependencyDescriptor> dependencies =
                new ArrayList<>();

        if (file == null || file.getContent() == null) {
            return dependencies;
        }

        String[] lines =
                file.getContent().split("\\R");

        for (String line : lines) {

            line = line.trim();

            if (!line.startsWith("require")) {
                continue;
            }

            line = line.replaceFirst("require", "").trim();

            String[] parts =
                    line.split("\\s+");

            if (parts.length < 2) {
                continue;
            }

            dependencies.add(

                    DependencyDescriptor.builder()

                            .groupId(parts[0])

                            .artifactId(parts[0])

                            .version(parts[1])

                            .scope("runtime")

                            .ecosystem("GO")

                            .build()

            );

        }

        return dependencies;

    }

}