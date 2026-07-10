package com.careeros.careeros_backend.service.github.evidence;

import java.util.Map;

public interface EngineeringEvidenceBuilder {

    EngineeringEvidenceBundle build(
            Map<String, String> importantFiles
    );

}