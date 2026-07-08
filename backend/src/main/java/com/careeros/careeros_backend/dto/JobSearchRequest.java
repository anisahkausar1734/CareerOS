package com.careeros.careeros_backend.dto;

import lombok.Data;

@Data
public class JobSearchRequest {

    private String role;

    private String city;

    private String workMode;

    private String experience;

    private String dreamCompany;
}