package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.StudentProfileRequest;
import com.careeros.careeros_backend.dto.StudentProfileResponse;
import com.careeros.careeros_backend.model.StudentProfile;
import com.careeros.careeros_backend.repository.StudentProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentProfileService {

    private final StudentProfileRepository
            studentProfileRepository;

   
    public StudentProfileResponse saveProfile(
            StudentProfileRequest request
    ) {

        StudentProfile profile =
                studentProfileRepository
                        .findByEmail(
                                request.getEmail()
                        )
                        .orElse(
                                StudentProfile
                                        .builder()
                                        .email(
                                                request.getEmail()
                                        )
                                        .build()
                        );

        profile.setFullName(
                request.getFullName()
        );

        profile.setPhoneNumber(
                request.getPhoneNumber()
        );

        profile.setCollegeName(
                request.getCollegeName()
        );

        profile.setDegree(
                request.getDegree()
        );

        profile.setBranch(
                request.getBranch()
        );

        profile.setCurrentYear(
                request.getCurrentYear()
        );

        profile.setGraduationYear(
                request.getGraduationYear()
        );

        profile.setDreamRole(
                request.getDreamRole()
        );

        profile.setSkills(
                request.getSkills()
        );

        profile.setHasResume(
        request.getHasResume()
);



if (profile.getSkillGapCompleted() == null) {
    profile.setSkillGapCompleted(false);
}

if (profile.getRoadmapCompleted() == null) {
    profile.setRoadmapCompleted(false);
}

if (profile.getResumeAnalysisCompleted() == null) {
    profile.setResumeAnalysisCompleted(false);
}

if (profile.getInterviewCompleted() == null) {
    profile.setInterviewCompleted(false);
}

if (profile.getApplicationsStarted() == null) {
    profile.setApplicationsStarted(false);
}

profile.setCareerReadiness(
        calculateReadiness(profile)
);
        profile.setCurrentStage(
                "PROFILE_COMPLETED"
        );

        StudentProfile savedProfile =
                studentProfileRepository
                        .save(profile);


        return mapToResponse(
                savedProfile
        );
    }

    public StudentProfileResponse getProfile(
            String email
    ) {

        StudentProfile profile =
                studentProfileRepository
                        .findByEmail(email)
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "Profile not found"
                                        )
                        );

        return mapToResponse(
                profile
        );

    }


public StudentProfileResponse updateProfile(
        String email,
        StudentProfileRequest request
) {

    StudentProfile profile =
            studentProfileRepository
                    .findByEmail(email)
                    .orElseThrow(
                            () -> new RuntimeException(
                                    "Profile not found"
                            )
                    );

    profile.setFullName(
            request.getFullName()
    );

    profile.setPhoneNumber(
            request.getPhoneNumber()
    );

    profile.setCollegeName(
            request.getCollegeName()
    );

    profile.setDegree(
            request.getDegree()
    );

    profile.setBranch(
            request.getBranch()
    );

    profile.setCurrentYear(
            request.getCurrentYear()
    );

    profile.setGraduationYear(
            request.getGraduationYear()
    );

    profile.setDreamRole(
            request.getDreamRole()
    );

    profile.setSkills(
            request.getSkills()
    );

    profile.setHasResume(
        request.getHasResume()
);

    profile.setCareerReadiness(
            calculateReadiness(profile)
    );

    StudentProfile updatedProfile =
            studentProfileRepository
                    .save(profile);

    return mapToResponse(
            updatedProfile
    );
}


public Integer calculateReadiness(
        StudentProfile profile
) {

    int score = 10;

    if(Boolean.TRUE.equals(
            profile.getSkillGapCompleted()
    )) {
        score += 20;
    }

    if(Boolean.TRUE.equals(
            profile.getRoadmapCompleted()
    )) {
        score += 25;
    }

    if(Boolean.TRUE.equals(
           profile.getResumeAnalysisCompleted()
    )) {
        score += 20;
    }

    if(Boolean.TRUE.equals(
            profile.getInterviewCompleted()
    )) {
        score += 15;
    }

    if(Boolean.TRUE.equals(
           profile.getApplicationsStarted()
    )) {
        score += 10;
    }

    return score;
}

    private StudentProfileResponse mapToResponse(
            StudentProfile profile
    ) {

        return StudentProfileResponse
                .builder()
                .email(
                        profile.getEmail()
                )
                .fullName(
                        profile.getFullName()
                )
                .phoneNumber(
                        profile.getPhoneNumber()
                )
                .collegeName(
                        profile.getCollegeName()
                )
                .degree(
                        profile.getDegree()
                )
                .branch(
                        profile.getBranch()
                )
                .currentYear(
                        profile.getCurrentYear()
                )
                .graduationYear(
                        profile.getGraduationYear()
                )
                .dreamRole(
                        profile.getDreamRole()
                )
                .skills(
                        profile.getSkills()
                )
                .careerReadiness(
                        profile.getCareerReadiness()
                )
                .currentStage(
                        profile.getCurrentStage()
                )
.hasResume(
        profile.getHasResume()
)

                .skillGapCompleted(
        profile.getSkillGapCompleted()
)

.roadmapCompleted(
        profile.getRoadmapCompleted()
)

.resumeAnalysisCompleted(
        profile.getResumeAnalysisCompleted()
)

.interviewCompleted(
        profile.getInterviewCompleted()
)

.applicationsStarted(
        profile.getApplicationsStarted()
)

.hasResume(
        profile.getHasResume()
)


                .build();
    }
}