package com.careeros.careeros_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResumeRefinementHistoryDTO {

    private String id;

    private String companyName;

    private LocalDateTime createdAt;
}