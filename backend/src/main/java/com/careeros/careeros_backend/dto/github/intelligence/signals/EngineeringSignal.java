package com.careeros.careeros_backend.dto.github.intelligence.signals;

import com.careeros.careeros_backend.dto.github.intelligence.enums.SignalCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EngineeringSignal {

    /*
     * Signal category
     */
private SignalCategory category;
    /*
     * Signal
     * Example:
     * REST API
     * Authentication
     * CI/CD
     */
    private String signal;

    /*
     * Why this signal exists
     */
    private String reasoning;

    /*
     * Evidence
     */
    private String evidence;

    /*
     * Engineering importance
     */
    private Integer importance;

    /*
     * Confidence
     */
    private Integer confidence;

}