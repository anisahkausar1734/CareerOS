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
public class StudentProfileRequest {

    private String email;

    private String fullName;

    private String phoneNumber;

    private String collegeName;

    private String degree;

    private String branch;

    private String currentYear;

    private Integer graduationYear;

    private String dreamRole;

    private List<String> skills;

    private Boolean hasResume;


    
}