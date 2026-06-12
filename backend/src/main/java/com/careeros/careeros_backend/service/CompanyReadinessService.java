package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.CompanyReadinessResponse;
import com.careeros.careeros_backend.exception.UserNotFoundException;
import com.careeros.careeros_backend.model.User;
import com.careeros.careeros_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    private List<String> getAmazonSkills() {

        return List.of(
                "Java",
                "DSA",
                "System Design",
                "AWS",
                "Git"
        );
    }

    private List<String> getMicrosoftSkills() {

        return List.of(
                "Java",
                "DSA",
                "Azure",
                "System Design",
                "Git"
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

    private List<String> getRequiredSkills(
            String company
    ) {

        if (company == null) {
            return List.of();
        }

        switch (company.toUpperCase()) {

            case "GOOGLE":
                return getGoogleSkills();

            case "AMAZON":
                return getAmazonSkills();

            case "MICROSOFT":
                return getMicrosoftSkills();

            case "STARTUP":
                return getStartupSkills();

            default:
                return List.of();
        }
    }

    public CompanyReadinessResponse getReadiness(
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

        String company =
                user.getDreamCompany();

        List<String> userSkills =
                user.getSkills() != null
                        ? user.getSkills()
                        : List.of();

        List<String> requiredSkills =
                getRequiredSkills(company);

        List<String> strengths =
                new ArrayList<>();

        List<String> missingSkills =
                new ArrayList<>();

        for (String skill : requiredSkills) {

            if (userSkills.contains(skill)) {

                strengths.add(skill);

            } else {

                missingSkills.add(skill);
            }
        }

        int readiness = 0;

        if (!requiredSkills.isEmpty()) {

            readiness =
                    (strengths.size() * 100)
                            / requiredSkills.size();
        }

        String feedback;

        if (readiness >= 80) {

            feedback =
                    "Excellent profile for "
                            + company;

        } else if (readiness >= 60) {

            feedback =
                    "Good profile. Improve missing skills.";

        } else {

            feedback =
                    "Focus on core skills and DSA preparation.";
        }

        return CompanyReadinessResponse.builder()
                .company(company)
                .readinessPercentage(readiness)
                .strengths(strengths)
                .missingSkills(missingSkills)
                .feedback(feedback)
                .build();
    }
}