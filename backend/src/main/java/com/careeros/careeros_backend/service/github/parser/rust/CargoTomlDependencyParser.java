package com.careeros.careeros_backend.service.github.parser.rust;

import com.careeros.careeros_backend.dto.github.dependency.DependencyDescriptor;
import com.careeros.careeros_backend.dto.github.source.RepositorySourceFile;
import com.careeros.careeros_backend.service.github.parser.DependencyParser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CargoTomlDependencyParser
        implements DependencyParser {

    @Override
    public boolean supports(
            RepositorySourceFile file
    ) {

        return file != null
                && "Cargo.toml".equalsIgnoreCase(
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

        boolean insideDependencies = false;

        String[] lines =
                file.getContent().split("\\R");

        for (String line : lines) {

            line = line.trim();

            if (line.startsWith("[dependencies]")) {

                insideDependencies = true;
                continue;

            }

            if (line.startsWith("[")
                    && !line.startsWith("[dependencies]")) {

                insideDependencies = false;

            }

            if (!insideDependencies
                    || line.isBlank()
                    || line.startsWith("#")) {

                continue;

            }

            String[] split =
                    line.split("=", 2);

            if (split.length != 2) {
                continue;
            }

            String name =
                    split[0].trim();

            String version =
                    split[1]
                            .replace("\"", "")
                            .trim();

            dependencies.add(

                    DependencyDescriptor.builder()

                            .artifactId(name)

                            .version(version)

                            .scope("runtime")

                            .ecosystem("CARGO")

                            .build()

            );

        }

        return dependencies;

    }

}