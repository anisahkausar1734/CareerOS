package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.AdminDashboardResponse;
import com.careeros.careeros_backend.model.User;
import com.careeros.careeros_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminDashboardService {

    private final UserRepository userRepository;

    public AdminDashboardResponse getDashboard() {

        List<User> users =
                userRepository.findAll();

        long totalUsers =
                users.size();

        long backendDevelopers =
                users.stream()
                        .filter(user ->
                                "Backend Developer"
                                        .equalsIgnoreCase(
                                                user.getTargetRole()
                                        ))
                        .count();

        long frontendDevelopers =
                users.stream()
                        .filter(user ->
                                "Frontend Developer"
                                        .equalsIgnoreCase(
                                                user.getTargetRole()
                                        ))
                        .count();

        double averageATSScore =
                0;

        if (!users.isEmpty()) {

            averageATSScore =
                    users.stream()
                            .mapToInt(user -> {

                                List<String> skills =
                                        user.getSkills() != null
                                                ? user.getSkills()
                                                : List.of();

                                int score =
                                        100
                                                - ((6 - Math.min(
                                                skills.size(),
                                                6
                                        )) * 10);

                                return Math.max(
                                        score,
                                        0
                                );
                            })
                            .average()
                            .orElse(0);
        }

        return AdminDashboardResponse
                .builder()
                .totalUsers(totalUsers)
                .backendDevelopers(
                        backendDevelopers
                )
                .frontendDevelopers(
                        frontendDevelopers
                )
                .averageATSScore(
                        averageATSScore
                )
                .build();
    }
}