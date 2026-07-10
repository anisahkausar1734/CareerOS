package com.careeros.careeros_backend.repository;

import com.careeros.careeros_backend.model.RepositoryEvidenceCache;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RepositoryEvidenceCacheRepository
        extends MongoRepository<RepositoryEvidenceCache,String> {

    Optional<RepositoryEvidenceCache>
    findByRepositoryUrl(
            String repositoryUrl
    );

}