package com.careeros.careeros_backend.repository;

import com.careeros.careeros_backend.model.ATSAnalysis;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ATSAnalysisRepository
        extends MongoRepository<ATSAnalysis, String> {

    Optional<ATSAnalysis> findByEmail(String email);

}