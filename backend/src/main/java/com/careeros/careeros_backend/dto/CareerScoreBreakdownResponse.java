package com.careeros.careeros_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CareerScoreBreakdownResponse {

    private Integer skillsScore;

    private Integer projectsScore;

    private Integer experienceScore;

    private Integer profileScore;

    private Integer overallScore;
}
