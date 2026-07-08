package com.careeros.careeros_backend.repository;

import com.careeros.careeros_backend.model.Application;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ApplicationRepository
        extends MongoRepository<Application, String> {

    List<Application> findByEmail(String email);
    long countByEmail(String email);

long countByEmailAndStatus(
        String email,
        String status
);
}