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
public class ATSSectionAnalysis {

    private String sectionName;

    private Integer score;

    private String status;

    private List<String> strengths;

    private List<String> issues;

    private List<String> recommendations;
}