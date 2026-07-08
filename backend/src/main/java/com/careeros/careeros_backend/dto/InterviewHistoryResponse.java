package com.careeros.careeros_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class InterviewHistoryResponse {

    private String id;

    private String role;

    private String company;

    private Double finalScore;

    private LocalDateTime endedAt;
}