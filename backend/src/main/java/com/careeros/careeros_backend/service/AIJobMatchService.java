package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.JobMatchResponse;
import com.careeros.careeros_backend.model.StudentProfile;
import com.careeros.careeros_backend.repository.StudentProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AIJobMatchService {

private final GeminiService geminiService;

private final StudentProfileRepository
        studentProfileRepository;

public JobMatchResponse analyzeJob(
        String email,
        String jobTitle
) {

    StudentProfile profile =
            studentProfileRepository
                    .findByEmail(email)
                    .orElseThrow(
                            () -> new RuntimeException(
                                    "Student profile not found"
                            )
                    );

    String prompt =

            "You are CareerOS Job Match Analyzer.\n\n"

                    + "Student Dream Role: "
                    + profile.getDreamRole()

                    + "\nStudent Skills: "
                    + profile.getSkills()

                    + "\nCareer Readiness: "
                    + profile.getCareerReadiness()

                    + "\nCurrent Stage: "
                    + profile.getCurrentStage()

                    + "\nMissing Skills: "
                    + profile.getLatestMissingSkills()

                    + "\nPriority Skills: "
                    + profile.getLatestPrioritySkills()

                    + "\nJob Title: "
                    + jobTitle

                    + "\n\nReturn EXACTLY in this format:\n\n"

                    + "Score: 80\n\n"

                    + "Strengths:\n"
                    + "- item\n"
                    + "- item\n\n"

                    + "Missing Skills:\n"
                    + "- item\n"
                    + "- item\n\n"

                    + "Recommendations:\n"
                    + "- item\n"
                    + "- item\n\n"

                    + "Summary:\n"
                    + "short summary";

    String response =
            geminiService.askGeminiCustom(
                    prompt
            );

    int score = 70;

    try {

        String firstLine =
                response.split("\n")[0];

        String number =
                firstLine.replaceAll(
                        "[^0-9]",
                        ""
                );

        score =
                Integer.parseInt(
                        number
                );

    } catch (Exception ignored) {
    }

    List<String> strengths =
            extractSection(
                    response,
                    "Strengths"
            );

    List<String> missingSkills =
            extractSection(
                    response,
                    "Missing Skills"
            );

    List<String> recommendations =
            extractSection(
                    response,
                    "Recommendations"
            );

    return JobMatchResponse
            .builder()
            .matchScore(score)
            .explanation(response)
            .strengths(strengths)
            .missingSkills(missingSkills)
            .recommendations(recommendations)
            .build();
}

private List<String> extractSection(
        String response,
        String sectionName
) {

    List<String> items =
            new ArrayList<>();

    try {

        String[] lines =
                response.split("\n");

        boolean capture = false;

        for (String line : lines) {

            line = line.trim();

            if (line.startsWith(sectionName)) {

                capture = true;
                continue;
            }

            if (capture &&
                    (
                            line.startsWith("Strengths:")
                                    || line.startsWith("Missing Skills:")
                                    || line.startsWith("Recommendations:")
                                    || line.startsWith("Summary:")
                    )) {

                break;
            }

            if (capture &&
                    line.startsWith("-")) {

                items.add(
                        line.replace("-", "")
                                .trim()
                );
            }
        }

    } catch (Exception ignored) {
    }

    return items;
}

}
