package com.careeros.careeros_backend.repository;

import com.careeros.careeros_backend.model.Roadmap;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoadmapRepository
        extends MongoRepository<Roadmap,String> {

    Optional<Roadmap> findByEmail(
            String email
    );

}