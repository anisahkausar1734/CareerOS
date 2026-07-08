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
public class RoadmapStepResponse {

  private Integer phase;

private String title;

private String description;

private Integer estimatedWeeks;

private Boolean completed;


private List<String> resources;

private String category;

private String reason;

private String impact;

private String priority;


}