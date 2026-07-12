package com.careeros.careeros_backend.service.github.builder;

import com.careeros.careeros_backend.dto.github.RepositoryEvidenceResponse;
import com.careeros.careeros_backend.dto.github.RepositoryRawEvidence;
import com.careeros.careeros_backend.dto.github.intelligence.RepositoryIntelligence;

import java.util.List;
import java.util.Map;

public interface RepositoryEvidenceBuilder {

   RepositoryEvidenceResponse build(

    RepositoryRawEvidence raw,

    RepositoryIntelligence intelligence

);

}