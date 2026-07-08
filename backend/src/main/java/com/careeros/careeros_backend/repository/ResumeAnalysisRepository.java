package com.careeros.careeros_backend.repository;

import com.careeros.careeros_backend.model.ResumeAnalysis;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ResumeAnalysisRepository
        extends MongoRepository<ResumeAnalysis, String> {

    Optional<ResumeAnalysis>
    findByEmail(String email);
}