package com.careeros.careeros_backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;

    // Authentication
    private String fullName;
    private String email;
    private String password;
    private String role;

    // Academic Details
    private String college;
    private String branch;
    private Integer graduationYear;

    // Career Details
    private String targetRole;
    private String experienceLevel;
    private List<String> skills;
    private List<String> interests;

    // Professional Links
    private String githubUrl;
    private String linkedinUrl;
    private String portfolioUrl;
    private String profileImageUrl;
    private Integer currentYear;
    private String targetDomain;
    private String dreamCompany;

    // Resume Information
    private String resumeUrl;
    private Double resumeScore;

    // Account Status
    private Boolean isActive;
    private Boolean isEmailVerified;

    // Timestamps
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoginAt;
}