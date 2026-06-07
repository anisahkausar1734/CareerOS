package com.careeros.careeros_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.careeros.careeros_backend.repository.UserRepository;
import java.util.List;

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

}