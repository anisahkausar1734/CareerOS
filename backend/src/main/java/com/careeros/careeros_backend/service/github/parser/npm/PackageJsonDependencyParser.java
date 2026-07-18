package com.careeros.careeros_backend.service.github.parser.npm;

import com.careeros.careeros_backend.dto.github.dependency.DependencyDescriptor;
import com.careeros.careeros_backend.dto.github.source.RepositorySourceFile;
import com.careeros.careeros_backend.service.github.parser.DependencyParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PackageJsonDependencyParser
        implements DependencyParser {

    private final ObjectMapper objectMapper;

    @Override
    public boolean supports(
            RepositorySourceFile file
    ) {

        return file != null
                && "package.json".equalsIgnoreCase(
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

        try {

            JsonNode root =
                    objectMapper.readTree(
                            file.getContent()
                    );

            extract(
                    root,
                    "dependencies",
                    dependencies
            );

            extract(
                    root,
                    "devDependencies",
                    dependencies
            );

            extract(
                    root,
                    "peerDependencies",
                    dependencies
            );

            extract(
                    root,
                    "optionalDependencies",
                    dependencies
            );

        }

        catch (Exception e) {

            throw new RuntimeException(
                    "Unable to parse package.json",
                    e
            );

        }

        return dependencies;

    }

    private void extract(

            JsonNode root,

            String nodeName,

            List<DependencyDescriptor> dependencies

    ) {

        JsonNode node =
                root.get(nodeName);

        if (node == null) {
            return;
        }

        Iterator<Map.Entry<String, JsonNode>> iterator =
                node.fields();

        while (iterator.hasNext()) {

            Map.Entry<String, JsonNode> entry =
                    iterator.next();

            dependencies.add(

                    DependencyDescriptor.builder()

                            .groupId("npm")

                            .artifactId(
                                    entry.getKey()
                            )

                            .version(
                                    entry.getValue().asText()
                            )

                            .scope(
                                    nodeName
                            )

                            .ecosystem("NPM")

                            .build()

            );

        }

    }

}