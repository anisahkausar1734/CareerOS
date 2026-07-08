package com.careeros.careeros_backend.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternshipSearchResponse {

    private List<InternshipResponse> internships;
}