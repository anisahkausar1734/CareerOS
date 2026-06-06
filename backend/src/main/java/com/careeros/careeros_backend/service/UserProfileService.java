package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.ProfileResponse;
import com.careeros.careeros_backend.dto.UpdateProfileRequest;
import com.careeros.careeros_backend.exception.UserNotFoundException;
import com.careeros.careeros_backend.model.User;
import com.careeros.careeros_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserRepository userRepository;

    public ProfileResponse getProfile(String email) {

        Optional<User> optionalUser =
                userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        User user = optionalUser.get();

        return mapToProfileResponse(user);
    }

    public ProfileResponse updateProfile(
            UpdateProfileRequest request
    ) {

        Optional<User> optionalUser =
                userRepository.findByEmail(request.getEmail());

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        User user = optionalUser.get();

        user.setCollege(request.getCollege());
        user.setBranch(request.getBranch());
        user.setGraduationYear(request.getGraduationYear());

        user.setTargetRole(request.getTargetRole());
        user.setExperienceLevel(request.getExperienceLevel());

        user.setSkills(request.getSkills());
        user.setInterests(request.getInterests());

        user.setGithubUrl(request.getGithubUrl());
        user.setLinkedinUrl(request.getLinkedinUrl());
        user.setPortfolioUrl(request.getPortfolioUrl());

        // Day 5 Context Fields
        user.setCurrentYear(request.getCurrentYear());
        user.setTargetDomain(request.getTargetDomain());
        user.setDreamCompany(request.getDreamCompany());

        User updatedUser =
                userRepository.save(user);

        return mapToProfileResponse(updatedUser);
    }

    private ProfileResponse mapToProfileResponse(User user) {

        return ProfileResponse.builder()
                .fullName(user.getFullName())
                .email(user.getEmail())

                .college(user.getCollege())
                .branch(user.getBranch())
                .graduationYear(user.getGraduationYear())

                .targetRole(user.getTargetRole())
                .experienceLevel(user.getExperienceLevel())

                .skills(user.getSkills())
                .interests(user.getInterests())

                .githubUrl(user.getGithubUrl())
                .linkedinUrl(user.getLinkedinUrl())
                .portfolioUrl(user.getPortfolioUrl())

                // Day 5 Context Fields
                .currentYear(user.getCurrentYear())
                .targetDomain(user.getTargetDomain())
                .dreamCompany(user.getDreamCompany())

                .build();
    }
}