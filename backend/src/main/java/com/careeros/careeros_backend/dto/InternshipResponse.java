package com.careeros.careeros_backend.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternshipResponse {

    private String title;

    private String company;

    private String location;

    private String description;

    private String applyUrl;

    private String salary;
}