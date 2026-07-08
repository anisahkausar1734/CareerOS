package com.careeros.careeros_backend.repository;

import com.careeros.careeros_backend.model.LearningVideo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LearningVideoRepository
        extends MongoRepository<LearningVideo, String> {

    Optional<LearningVideo>
    findBySkillIgnoreCase(
            String skill
    );

}