package com.careeros.careeros_backend.service.github.technology;

import com.careeros.careeros_backend.dto.github.dependency.DependencyDescriptor;
import com.careeros.careeros_backend.dto.github.technology.TechnologyDescriptor;
import com.careeros.careeros_backend.dto.github.technology.TechnologyRegistryEntry;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TechnologyRegistryImpl
        implements TechnologyRegistry {

    private final ObjectMapper objectMapper;

    private final Map<String, TechnologyDescriptor> registry =
            new HashMap<>();

    @PostConstruct
    public void loadRegistry() {

        try {

            InputStream inputStream =
                    new ClassPathResource(
                            "technology-registry.json"
                    ).getInputStream();

            List<TechnologyRegistryEntry> entries =
                    objectMapper.readValue(
                            inputStream,
                            new TypeReference<List<TechnologyRegistryEntry>>() {}
                    );

            for (TechnologyRegistryEntry entry : entries) {

                registry.put(

                        key(
                                entry.getGroupId(),
                                entry.getArtifactId()
                        ),

                        entry.getTechnology()

                );

            }

        }

        catch (Exception e) {

            throw new RuntimeException(
                    "Unable to load technology registry.",
                    e
            );

        }

    }

    @Override
    public Optional<TechnologyDescriptor> resolve(
            DependencyDescriptor dependency
    ) {

        return Optional.ofNullable(

                registry.get(

                        key(

                                dependency.getGroupId(),

                                dependency.getArtifactId()

                        )

                )

        );

    }

    private String key(

            String group,

            String artifact

    ) {

        return group + ":" + artifact;

    }

}