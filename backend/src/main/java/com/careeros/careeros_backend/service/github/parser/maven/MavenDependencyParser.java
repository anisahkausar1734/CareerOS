package com.careeros.careeros_backend.service.github.parser.maven;

import com.careeros.careeros_backend.dto.github.dependency.DependencyDescriptor;
import com.careeros.careeros_backend.dto.github.source.RepositorySourceFile;
import com.careeros.careeros_backend.service.github.parser.DependencyParser;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class MavenDependencyParser
        implements DependencyParser {

    @Override
    public boolean supports(
            RepositorySourceFile file
    ) {

        return file != null
                && "pom.xml".equalsIgnoreCase(
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

            Document document =
                    DocumentBuilderFactory
                            .newInstance()
                            .newDocumentBuilder()
                            .parse(
                                    new ByteArrayInputStream(
                                            file.getContent()
                                                    .getBytes(StandardCharsets.UTF_8)
                                    )
                            );

            NodeList dependencyNodes =
                    document.getElementsByTagName(
                            "dependency"
                    );

            for (int i = 0; i < dependencyNodes.getLength(); i++) {

                Element dependency =
                        (Element) dependencyNodes.item(i);

                dependencies.add(

                        DependencyDescriptor.builder()

                                .groupId(
                                        getValue(
                                                dependency,
                                                "groupId"
                                        )
                                )

                                .artifactId(
                                        getValue(
                                                dependency,
                                                "artifactId"
                                        )
                                )

                                .version(
                                        getValue(
                                                dependency,
                                                "version"
                                        )
                                )

                                .scope(
                                        getValue(
                                                dependency,
                                                "scope"
                                        )
                                )

                                .ecosystem(
                                        "MAVEN"
                                )

                                .build()

                );

            }

        }

        catch (Exception e) {

            throw new RuntimeException(
                    "Unable to parse pom.xml",
                    e
            );

        }

        return dependencies;

    }

    private String getValue(

            Element parent,

            String tag

    ) {

        NodeList list =
                parent.getElementsByTagName(tag);

        if (list.getLength() == 0) {
            return "";
        }

        return list.item(0)
                .getTextContent()
                .trim();

    }

}