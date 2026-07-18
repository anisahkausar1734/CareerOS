package com.careeros.careeros_backend.service.github.intelligence.facts;

import com.careeros.careeros_backend.dto.github.intelligence.enums.FactCategory;
import com.careeros.careeros_backend.dto.github.intelligence.facts.RepositoryFact;
import com.careeros.careeros_backend.dto.github.technology.DetectedTechnology;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TechnologyFactBuilderImpl
        implements TechnologyFactBuilder {

  @Override
public List<RepositoryFact> build(
        List<DetectedTechnology> technologies
) {

    List<RepositoryFact> facts =

            technologies.stream()

                    .map(t ->

                            RepositoryFact.builder()

                                    .category(
                                            FactCategory.BUILD
                                    )

                                    .key(
                                            t.getTechnology()
                                                    .getCategory()
                                    )

                                    .value(
                                            t.getTechnology()
                                                    .getName()
                                    )

                                    .source(
                                            t.getDependency().getGroupId()
                                                    + ":"
                                                    + t.getDependency().getArtifactId()
                                    )

                                    .confidence(
                                            t.getTechnology()
                                                    .getConfidence()
                                    )

                                    .build()

                    )

                    .toList();

    System.out.println("========== TECHNOLOGY FACTS ==========");

    facts.forEach(System.out::println);

    return facts;

}

}