package com.careeros.careeros_backend.dto.github.evidence;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryDocumentation {

    private Boolean hasReadme;

    private String readme;

    private Boolean hasWiki;

    private Boolean hasContributingGuide;

    private Boolean hasCodeOfConduct;

    private Boolean hasLicense;

    private Boolean hasChangelog;

}