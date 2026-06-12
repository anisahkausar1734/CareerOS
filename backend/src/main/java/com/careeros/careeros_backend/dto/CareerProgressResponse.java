package com.careeros.careeros_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CareerProgressResponse {

    private Integer resourcesCompleted;

    private Integer projectsCompleted;

    private Integer certificationsCompleted;

    private Integer overallProgress;
}
