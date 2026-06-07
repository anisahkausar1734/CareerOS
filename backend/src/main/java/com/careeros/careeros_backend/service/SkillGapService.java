package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.SkillGapResponse;
import com.careeros.careeros_backend.exception.UserNotFoundException;
import com.careeros.careeros_backend.model.User;
import com.careeros.careeros_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillGapService {

    private final UserRepository userRepository;

    private List<String> getBackendDeveloperSkills() {

        return List.of(
                "Java",
                "Spring Boot",
                "REST APIs",
                "SQL",
                "MongoDB",
                "Git",
                "Docker",
                "Redis",
                "AWS"
        );
    }

    private List<String> getRequiredSkills(
            String targetRole
    ) {

        if ("Backend Developer"
                .equalsIgnoreCase(targetRole)) {

            return getBackendDeveloperSkills();
        }

        return List.of();
    }

    public SkillGapResponse getSkillGap(
            String targetRole,
            List<String> currentSkills
    ) {

        List<String> requiredSkills =
                getRequiredSkills(targetRole);

        List<String> missingSkills =
                requiredSkills.stream()
                        .filter(skill ->
                                !currentSkills.contains(skill))
                        .toList();

        int matchPercentage = 0;

        if (!requiredSkills.isEmpty()) {

            matchPercentage =
                    ((requiredSkills.size()
                            - missingSkills.size())
                            * 100)
                            / requiredSkills.size();
        }

        return SkillGapResponse.builder()
                .targetRole(targetRole)
                .currentSkills(currentSkills)
                .missingSkills(missingSkills)
                .skillMatchPercentage(matchPercentage)
                .build();
    }

    public SkillGapResponse getSkillGap(
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

        return getSkillGap(
                user.getTargetRole(),
                user.getSkills()
        );
    }
}