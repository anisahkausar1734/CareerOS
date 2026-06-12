package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.JobRecommendationResponse;
import com.careeros.careeros_backend.exception.UserNotFoundException;
import com.careeros.careeros_backend.model.User;
import com.careeros.careeros_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobRecommendationService {

    private final UserRepository userRepository;

    public JobRecommendationResponse getRecommendations(
            String email
    ) {

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow(
                                () -> new UserNotFoundException(
                                        "User not found"
                                )
                        );

        String targetRole =
                user.getTargetRole();

        if (targetRole == null ||
                targetRole.isBlank()) {

            targetRole =
                    "Software Engineer";
        }

        List<String> jobs =
                switch (targetRole) {

                    case "Backend Developer" ->
                            List.of(
                                    "Junior Backend Developer",
                                    "Java Developer Intern",
                                    "Spring Boot Developer",
                                    "API Developer",
                                    "Software Engineer I"
                            );

                    case "Frontend Developer" ->
                            List.of(
                                    "React Developer",
                                    "Frontend Intern",
                                    "UI Developer",
                                    "JavaScript Developer",
                                    "Frontend Engineer"
                            );

                    case "Full Stack Developer" ->
                            List.of(
                                    "Full Stack Developer",
                                    "Software Engineer",
                                    "MERN Stack Developer",
                                    "Web Developer",
                                    "Application Developer"
                            );

                    default ->
                            List.of(
                                    "Software Engineer",
                                    "Developer Intern",
                                    "Associate Software Engineer"
                            );
                };

        return JobRecommendationResponse
                .builder()
                .targetRole(targetRole)
                .recommendedJobs(jobs)
                .build();
    }
}