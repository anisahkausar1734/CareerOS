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
            return AuthResponse.builder()
                    .success(false)
                    .message("Email already exists")
                    .build();
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
                .build();
    }
}