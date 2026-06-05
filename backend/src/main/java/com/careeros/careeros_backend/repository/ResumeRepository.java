package com.careeros.careeros_backend.repository;

import com.careeros.careeros_backend.model.Resume;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ResumeRepository
        extends MongoRepository<Resume, String> {

    Optional<Resume> findByEmail(String email);
}