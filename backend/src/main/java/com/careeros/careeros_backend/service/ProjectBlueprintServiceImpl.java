package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.ProjectBlueprintResponse;
import com.careeros.careeros_backend.model.ProjectBlueprint;
import com.careeros.careeros_backend.repository.ProjectBlueprintRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectBlueprintServiceImpl
        implements ProjectBlueprintService {

    private final GeminiService
            geminiService;

    private final ProjectBlueprintRepository
            projectBlueprintRepository;

    private final ObjectMapper
            objectMapper;

    @Override
    public ProjectBlueprintResponse
    getBlueprint(
            String projectName
    )
    {
        ProjectBlueprintResponse cached =
                getCachedBlueprint(
                        projectName
                );

        if(cached != null)
        {
            return cached;
        }

        return generateBlueprint(
                projectName
        );
    }

    @Override
    public ProjectBlueprintResponse
    regenerateBlueprint(
            String projectName
    )
    {
        projectBlueprintRepository
                .deleteByProjectName(
                        projectName
                );

        return generateBlueprint(
                projectName
        );
    }

    public ProjectBlueprintResponse
    getCachedBlueprint(
            String projectName
    )
    {
        return projectBlueprintRepository

                .findByProjectName(
                        projectName
                )

                .map(
                        blueprint -> {

                            try {

                                return objectMapper
                                        .readValue(
                                                blueprint.getBlueprintJson(),
                                                ProjectBlueprintResponse.class
                                        );

                            }
                            catch(Exception e)
                            {
                                return null;
                            }

                        }
                )

                .orElse(null);
    }

    private ProjectBlueprintResponse
    generateBlueprint(
            String projectName
    )
    {
        String prompt = """
You are a senior software architect.

Generate a project blueprint.

Project:
%s

Return exactly in this format:

DIFFICULTY:
DURATION:
TECH_STACK:
FEATURES:
CAREER_IMPACT:
RESUME_IMPACT:
INTERNSHIP_IMPACT:
ROADMAP:

TECH_STACK and FEATURES should be comma separated.
ROADMAP should be a short implementation plan.

Keep response concise.
"""
.formatted(
        projectName
);

        String geminiResponse =
                geminiService
                        .askGeminiCustom(
                                prompt
                        );

        String difficulty =
                extract(
                        geminiResponse,
                        "DIFFICULTY:"
                );

        String duration =
                extract(
                        geminiResponse,
                        "DURATION:"
                );

        String techStack =
                extract(
                        geminiResponse,
                        "TECH_STACK:"
                );

        String features =
                extract(
                        geminiResponse,
                        "FEATURES:"
                );

        String careerImpact =
                extract(
                        geminiResponse,
                        "CAREER_IMPACT:"
                );

        String resumeImpact =
                extract(
                        geminiResponse,
                        "RESUME_IMPACT:"
                );

        String internshipImpact =
                extract(
                        geminiResponse,
                        "INTERNSHIP_IMPACT:"
                );

        String roadmap =
                extract(
                        geminiResponse,
                        "ROADMAP:"
                );

        ProjectBlueprintResponse response =
                ProjectBlueprintResponse
                        .builder()

                        .projectName(
                                projectName
                        )

                        .difficulty(
                                difficulty
                        )

                        .duration(
                                duration
                        )

                        .techStack(
                                java.util.Arrays.asList(
                                        techStack.split(",")
                                )
                        )

                        .features(
                                java.util.Arrays.asList(
                                        features.split(",")
                                )
                        )

                        .careerImpact(
                                careerImpact
                        )

                        .resumeImpact(
                                resumeImpact
                        )

                        .internshipImpact(
                                internshipImpact
                        )

                        .roadmap(
                                roadmap
                        )

                        .build();

        try {

            ProjectBlueprint blueprint =
                    ProjectBlueprint
                            .builder()

                            .projectName(
                                    projectName
                            )

                            .blueprintJson(
                                    objectMapper
                                            .writeValueAsString(
                                                    response
                                            )
                            )

                            .createdAt(
                                    java.time.LocalDateTime.now()
                            )

                            .build();

            projectBlueprintRepository
                    .save(
                            blueprint
                    );

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return response;
    }

    private String extract(
            String text,
            String key
    )
    {
        for(
            String line :
            text.split("\n")
        )
        {
            if(
                line.startsWith(
                        key
                )
            )
            {
                return line.replace(
                        key,
                        ""
                ).trim();
            }
        }

        return "";
    }

}