package com.careeros.careeros_backend.repository;

import com.careeros.careeros_backend.model.SkillGapAnalysis;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SkillGapAnalysisRepository
        extends MongoRepository<SkillGapAnalysis, String> {

    Optional<SkillGapAnalysis> findByEmail(
            String email
    );

}