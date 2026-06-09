package com.careeros.careeros_backend.service;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;
import com.careeros.careeros_backend.model.User;
import com.careeros.careeros_backend.dto.InternshipReadinessResponse;
import com.careeros.careeros_backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class InternshipReadinessService {
    private final UserRepository userRepository;
    public InternshipReadinessResponse
getReadiness(
        String email
) {

    User user =
            userRepository
                    .findByEmail(email)
                    .orElseThrow();

    int score = 0;

    if (user.getSkills() != null
            && !user.getSkills().isEmpty()) {

        score += 25;
    }

    if (user.getGithubUrl() != null
            && !user.getGithubUrl().isBlank()) {

        score += 25;
    }

    if (user.getLinkedinUrl() != null
            && !user.getLinkedinUrl().isBlank()) {

        score += 25;
    }

    if (user.getTargetRole() != null
            && !user.getTargetRole().isBlank()) {

        score += 25;
    }

    return InternshipReadinessResponse
            .builder()
            .readinessScore(score)
            .status(
                    score >= 80
                            ? "Internship Ready"
                            : score >= 50
                            ? "Moderately Ready"
                            : "Needs Improvement"
            )
            .strengths(
                    List.of(
                            "Profile Created"
                    )
            )
            .weaknesses(
                    List.of(
                            "Add More Skills"
                    )
            )
            .build();
}

}