package com.careeros.careeros_backend.dto.github.technology;

import com.careeros.careeros_backend.dto.github.dependency.DependencyDescriptor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetectedTechnology {

    /*
     * Technology metadata from registry
     */
    private TechnologyDescriptor technology;

    /*
     * Actual dependency found
     */
    private DependencyDescriptor dependency;

}