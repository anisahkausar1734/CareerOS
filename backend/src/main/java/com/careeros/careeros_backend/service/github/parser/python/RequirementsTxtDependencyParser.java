package com.careeros.careeros_backend.service.github.parser.python;

import com.careeros.careeros_backend.dto.github.dependency.DependencyDescriptor;
import com.careeros.careeros_backend.dto.github.source.RepositorySourceFile;
import com.careeros.careeros_backend.service.github.parser.DependencyParser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RequirementsTxtDependencyParser
        implements DependencyParser {

    @Override
    public boolean supports(
            RepositorySourceFile file
    ) {

        return file != null
                && "requirements.txt".equalsIgnoreCase(
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

            if (line.isBlank()
                    || line.startsWith("#")) {
                continue;
            }

            String name = line;
            String version = "";

            if (line.contains("==")) {

                String[] split =
                        line.split("==", 2);

                name = split[0].trim();
                version = split[1].trim();

            }
            else if (line.contains(">=")) {

                String[] split =
                        line.split(">=", 2);

                name = split[0].trim();
                version = ">=" + split[1].trim();

            }
            else if (line.contains("<=")) {

                String[] split =
                        line.split("<=", 2);

                name = split[0].trim();
                version = "<=" + split[1].trim();

            }

            dependencies.add(

                    DependencyDescriptor.builder()

                            .artifactId(name)

                            .version(version)

                            .scope("runtime")

                            .ecosystem("PYTHON")

                            .build()

            );

        }

        return dependencies;

    }

}