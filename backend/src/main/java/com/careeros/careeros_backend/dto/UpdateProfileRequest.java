package com.careeros.careeros_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileRequest {

    private String email;

    private String college;
    private String branch;
    private Integer graduationYear;

    private String targetRole;
    private String experienceLevel;

    private List<String> skills;
    private List<String> interests;

    private String githubUrl;
    private String linkedinUrl;
    private String portfolioUrl;
    private Integer currentYear;

private String targetDomain;

private String dreamCompany;
}