package com.careeros.careeros_backend.repository;

import com.careeros.careeros_backend.model.RepositoryEvidence;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RepositoryEvidenceRepository
        extends MongoRepository<RepositoryEvidence,String> {

    Optional<RepositoryEvidence>
    findByRepositoryUrl(
            String repositoryUrl
    );

}