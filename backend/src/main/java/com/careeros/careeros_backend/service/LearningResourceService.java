package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.LearningResourceResponse;
import com.careeros.careeros_backend.exception.UserNotFoundException;
import com.careeros.careeros_backend.model.User;
import com.careeros.careeros_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LearningResourceService {

    private final UserRepository userRepository;

    public LearningResourceResponse getResources(
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

        String role =
                user.getTargetRole();

        if (role == null ||
                role.isBlank()) {

            role =
                    "Software Engineer";
        }

        List<String> resources =
                switch (role) {

                    case "Backend Developer" ->
                            List.of(
                                    "Java Brains Spring Boot",
                                    "CodeWithMosh Java",
                                    "MongoDB University",
                                    "Docker for Beginners",
                                    "LeetCode DSA"
                            );

                    case "Frontend Developer" ->
                            List.of(
                                    "React Official Docs",
                                    "JavaScript.info",
                                    "Frontend Masters",
                                    "CSS Tricks",
                                    "FreeCodeCamp React"
                            );

                    default ->
                            List.of(
                                    "Roadmap.sh",
                                    "FreeCodeCamp",
                                    "CS50",
                                    "LeetCode",
                                    "GitHub Learning Lab"
                            );
                };

        return LearningResourceResponse
                .builder()
                .targetRole(role)
                .resources(resources)
                .build();
    }
}
