package com.careeros.careeros_backend.repository;

import com.careeros.careeros_backend.model.StudentProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentProfileRepository
        extends MongoRepository<StudentProfile, String> {

    Optional<StudentProfile> findByEmail(
            String email
    );
}