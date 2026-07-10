package com.careeros.careeros_backend.dto.projectanalysis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisMetadata {

    /**
     * GitHub repository analyzed.
     */
    private String repositoryUrl;

    /**
     * SHA or fingerprint used.
     */
    private String repositoryFingerprint;

    /**
     * Gemini model used.
     */
    private String aiModel;

    /**
     * Prompt version.
     */
    private String promptVersion;

    /**
     * Repository evidence version.
     */
    private String evidenceVersion;

    /**
     * Analysis timestamp.
     */
    private LocalDateTime analyzedAt;

    /**
     * Whether cache was used.
     */
    private Boolean cached;

    /**
     * Overall confidence (0–100).
     */
    private Integer confidence;

}