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
public class ATSAnalysisResponse {

    private Integer atsScore;

    private List<String> missingSections;

    private List<String> missingKeywords;

    private List<ATSImprovement> improvements;

    private List<ResumeSectionStatus> sectionStatus;

    private String feedback;
}