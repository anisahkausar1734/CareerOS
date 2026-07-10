package com.careeros.careeros_backend.dto.github;

import com.careeros.careeros_backend.dto.github.evidence.EngineeringEvidence;
import com.careeros.careeros_backend.dto.github.evidence.RepositoryActivity;
import com.careeros.careeros_backend.dto.github.evidence.RepositoryDeployment;
import com.careeros.careeros_backend.dto.github.evidence.RepositoryDocumentation;
import com.careeros.careeros_backend.dto.github.evidence.RepositoryHealth;
import com.careeros.careeros_backend.dto.github.evidence.RepositoryIdentity;
import com.careeros.careeros_backend.dto.github.evidence.RepositoryStructure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryEvidenceResponse {

    /*
     * Basic Repository Information
     */
    private RepositoryIdentity identity;

    /*
     * Repository Health & Activity Metrics
     */
    private RepositoryHealth health;

    /*
     * Repository Structure & Organization
     */
    private RepositoryStructure structure;

    /*
     * Documentation & Project Information
     */
    private RepositoryDocumentation documentation;

    /*
     * Development Activity
     */
    private RepositoryActivity activity;

    /*
     * Deployment Information
     */
    private RepositoryDeployment deployment;

    /*
     * Complete Engineering Evidence
     * (This is what Gemini will analyze)
     */
    private EngineeringEvidence engineeringEvidence;

}