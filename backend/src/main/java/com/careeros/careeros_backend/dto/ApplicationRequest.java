package com.careeros.careeros_backend.dto;

import lombok.Data;

@Data
public class ApplicationRequest {

    private String email;
    private String company;
    private String role;
    private String status;
    private String notes;
}