package com.careeros.careeros_backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResumeRefinementResponseDTO {

    private String refinedResume;

    private List<String> changesMade;

    private List<String> atsKeywords;

    private String summary;

}