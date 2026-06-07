package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.CompanyReadinessResponse;
import com.careeros.careeros_backend.exception.UserNotFoundException;
import com.careeros.careeros_backend.model.User;
import com.careeros.careeros_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyReadinessService {

    private final UserRepository userRepository;

    private List<String> getGoogleSkills() {

        return List.of(
                "Java",
                "DSA",
                "System Design",
                "Git",
                "Problem Solving",
                "Projects"
        );
    }

    private List<String> getStartupSkills() {

        return List.of(
                "Java",
                "Spring Boot",
                "MongoDB",
                "Git",
                "Docker",
                "Deployment",
                "Projects"
        );
    }

    public CompanyReadinessResponse getGoogleReadiness(
            String email
    ) {

        Optional<User> optionalUser =
                userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(
                    "User not found"
            );
        }

        User user = optionalUser.get();

        List<String> userSkills =
                user.getSkills();

        List<String> requiredSkills =
                getGoogleSkills();

        long matchedSkills =
                requiredSkills.stream()
                        .filter(userSkills::contains)
                        .count();

        int readiness =
                (int) ((matchedSkills * 100)
                        / requiredSkills.size());

        return CompanyReadinessResponse.builder()
                .company("GOOGLE")
                .readinessPercentage(readiness)
                .feedback(
                        readiness >= 70
                                ? "Strong profile for Google"
                                : "Need stronger DSA and problem solving"
                )
                .build();
    }
}