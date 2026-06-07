package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.YearReadinessResponse;
import com.careeros.careeros_backend.exception.UserNotFoundException;
import com.careeros.careeros_backend.model.User;
import com.careeros.careeros_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class YearExpectationService {

    private final UserRepository userRepository;
    private List<String> getYear1Expectations() {

    return List.of(
            "Programming Basics",
            "Git",
            "1 Project"
    );
}
private List<String> getYear2Expectations() {

    return List.of(
            "DSA",
            "Git",
            "Web Development",
            "3 Projects"
    );
}
private List<String> getYear3Expectations() {

    return List.of(
            "Internship",
            "Deployment",
            "Advanced Projects",
            "GitHub"
    );
}
private List<String> getYear4Expectations() {

    return List.of(
            "Internship",
            "Strong Projects",
            "Interview Readiness",
            "GitHub"
    );
}
private List<String> getExpectedSkills(
        Integer currentYear
) {

    switch (currentYear) {

        case 1:
            return getYear1Expectations();

        case 2:
            return getYear2Expectations();

        case 3:
            return getYear3Expectations();

        case 4:
            return getYear4Expectations();

        default:
            return List.of();
    }
}
public YearReadinessResponse getYearReadiness(
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
if (user.getCurrentYear() == null) {
    throw new IllegalArgumentException(
            "Current year is not set for this user"
    );
}
    List<String> expectedSkills =
            getExpectedSkills(
                    user.getCurrentYear()
            );

    List<String> userSkills =
            user.getSkills();

    List<String> missingSkills =
            expectedSkills.stream()
                    .filter(skill ->
                            !userSkills.contains(skill))
                    .toList();

    int readinessPercentage = 0;

    if (!expectedSkills.isEmpty()) {

        readinessPercentage =
                ((expectedSkills.size()
                        - missingSkills.size())
                        * 100)
                        / expectedSkills.size();
    }

    return YearReadinessResponse.builder()
            .currentYear(user.getCurrentYear())
            .readinessPercentage(readinessPercentage)
            .expectedSkills(expectedSkills)
            .missingSkills(missingSkills)
            .build();
}

}