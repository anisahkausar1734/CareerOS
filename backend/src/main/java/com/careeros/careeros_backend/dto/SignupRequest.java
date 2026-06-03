package com.careeros.careeros_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    private String fullName;

    private String email;

    private String password;

    private String college;

    private String branch;

    private Integer graduationYear;

    private String targetRole;
}                                                               