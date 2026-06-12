package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.CareerProgressResponse;
import com.careeros.careeros_backend.exception.UserNotFoundException;
import com.careeros.careeros_backend.model.User;
import com.careeros.careeros_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CareerProgressService {

    private final UserRepository userRepository;

    public CareerProgressResponse getProgress(
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

        int resources =
                user.getResourcesCompleted() != null
                        ? user.getResourcesCompleted()
                        : 0;

        int projects =
                user.getProjectsCompleted() != null
                        ? user.getProjectsCompleted()
                        : 0;

        int certifications =
                user.getCertificationsCompleted() != null
                        ? user.getCertificationsCompleted()
                        : 0;

        int overallProgress =
                Math.min(
                        ((resources * 10)
                        + (projects * 20)
                        + (certifications * 15)),
                        100
                );

        return CareerProgressResponse
                .builder()
                .resourcesCompleted(resources)
                .projectsCompleted(projects)
                .certificationsCompleted(certifications)
                .overallProgress(overallProgress)
                .build();
    }
}