package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.JobListing;
import com.careeros.careeros_backend.dto.JobRecommendationResponse;
import com.careeros.careeros_backend.dto.JobSearchRequest;
import com.careeros.careeros_backend.exception.UserNotFoundException;
import com.careeros.careeros_backend.model.User;
import com.careeros.careeros_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InternshipRecommendationService {

    private final UserRepository userRepository;

    private final AdzunaJobService adzunaJobService;

   public JobRecommendationResponse
getInternships(
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

       List<JobListing> jobs =
        adzunaJobService.searchJobs(
                targetRole
                        + " internship",
                ""
        );

        return JobRecommendationResponse
                .builder()
                .targetRole(targetRole)
                .jobs(jobs)
                .build();
    }

    public JobRecommendationResponse searchJobs(
            JobSearchRequest request
    ) {

        StringBuilder query =
                new StringBuilder();

        if (request.getRole() != null
                && !request.getRole().isBlank()) {

            query.append(
                    request.getRole()
            ).append(" ");
        }

        if (request.getCity() != null
                && !request.getCity().isBlank()) {

            query.append(
                    request.getCity()
            ).append(" ");
        }

        if (request.getWorkMode() != null
                && !request.getWorkMode().isBlank()) {

            query.append(
                    request.getWorkMode()
            ).append(" ");
        }

        if (request.getExperience() != null
                && !request.getExperience().isBlank()) {

            query.append(
                    request.getExperience()
            ).append(" ");
        }

        if (request.getDreamCompany() != null
                && !request.getDreamCompany().isBlank()) {

            query.append(
                    request.getDreamCompany()
            );
        }

      List<JobListing> jobs =
        adzunaJobService.searchJobs(
                request.getRole()
                        + " internship",
                request.getCity()
        );

        return JobRecommendationResponse
                .builder()
                .targetRole(
                        request.getRole()
                )
                .jobs(jobs)
                .build();
    }
}