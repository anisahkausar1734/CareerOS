package com.careeros.careeros_backend.repository;

import com.careeros.careeros_backend.model.LearningResource;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LearningResourceRepository
        extends MongoRepository<
                LearningResource,
                String
                > {

    Optional<LearningResource>
    findBySkillIgnoreCase(
            String skill
    );
}