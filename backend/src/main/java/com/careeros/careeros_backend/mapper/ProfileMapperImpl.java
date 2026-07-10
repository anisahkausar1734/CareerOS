package com.careeros.careeros_backend.mapper;

import com.careeros.careeros_backend.dto.ProfileResponse;
import com.careeros.careeros_backend.model.StudentProfile;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapperImpl
        implements ProfileMapper {

    @Override
    public ProfileResponse toResponse(
            StudentProfile profile
    ) {

        if (profile == null) {
            return null;
        }

        return ProfileResponse.builder()

                .fullName(profile.getFullName())

                .email(profile.getEmail())

                .college(profile.getCollegeName())

                .branch(profile.getBranch())

                .graduationYear(profile.getGraduationYear())

                .targetRole(profile.getDreamRole())

                .experienceLevel(profile.getCurrentStage())

                .skills(profile.getSkills())

                // StudentProfile currently doesn't have interests
                .interests(null)

                // StudentProfile currently doesn't store these
                .githubUrl(null)
                .linkedinUrl(null)
                .portfolioUrl(null)

                

                // StudentProfile currently doesn't have these
                .targetDomain(null)
                .dreamCompany(null)

                .build();

    }

}