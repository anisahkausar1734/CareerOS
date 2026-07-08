package com.careeros.careeros_backend.dto;

import lombok.Data;

@Data
public class InternshipMatchRequestDTO {

    private String email;

    private String company;

    private String role;

    private String description;
}