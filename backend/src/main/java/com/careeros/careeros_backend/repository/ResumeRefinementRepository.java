package com.careeros.careeros_backend.repository;

import com.careeros.careeros_backend.model.ResumeRefinement;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ResumeRefinementRepository
        extends MongoRepository<
                ResumeRefinement,
                String
        > {

    List<ResumeRefinement>
    findByEmailOrderByCreatedAtDesc(
            String email
    );
}