package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.AuthResponse;
import com.careeros.careeros_backend.dto.SignupRequest;
import com.careeros.careeros_backend.model.User;
import com.careeros.careeros_backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.careeros.careeros_backend.dto.LoginRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String testConnection() {
        return "Auth Service Working!";
    }

    public AuthResponse signup(SignupRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return buildResponse(
        false,
        "Email already exists"
);
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())

                // BCrypt Encryption
                .password(passwordEncoder.encode(request.getPassword()))

                .college(request.getCollege())
                .branch(request.getBranch())
                .graduationYear(request.getGraduationYear())
                .targetRole(request.getTargetRole())

                .experienceLevel("Beginner")
                .skills(new ArrayList<>())
                .interests(new ArrayList<>())

                .isActive(true)
                .isEmailVerified(false)

                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .success(true)
                .message("User registered successfully")
                .email(user.getEmail())
                .fullName(user.getFullName())
                .build();
    }
    public AuthResponse login(LoginRequest request) {

    Optional<User> optionalUser =
            userRepository.findByEmail(request.getEmail());

    if (optionalUser.isEmpty()) {
        return buildResponse(
        false,
        "User not found"
);
    }

    User user = optionalUser.get();

    boolean passwordMatches =
            passwordEncoder.matches(
                    request.getPassword(),
                    user.getPassword()
            );

    if (!passwordMatches) {
       return buildResponse(
        false,
        "Invalid password"
);
    }

    return AuthResponse.builder()
        .success(true)
        .message("Login successful")
        .email(user.getEmail())
        .fullName(user.getFullName())
        .build();
}
private AuthResponse buildResponse(
        boolean success,
        String message
) {
    return AuthResponse.builder()
            .success(success)
            .message(message)
            .build();
}
}