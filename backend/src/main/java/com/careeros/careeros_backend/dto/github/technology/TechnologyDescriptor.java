package com.careeros.careeros_backend.dto.github.technology;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyDescriptor {

    private String id;

    private String name;

    private String category;

    private String ecosystem;

    private Integer confidence;

}