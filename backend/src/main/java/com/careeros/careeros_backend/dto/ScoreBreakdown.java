package com.careeros.careeros_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoreBreakdown {

    private Integer projectScore;

    private Integer skillsScore;

    private Integer experienceScore;

    private Integer achievementScore;

    private Integer resumeQualityScore;

    private Integer finalScore;
}