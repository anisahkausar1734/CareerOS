package com.careeros.careeros_backend.repository;

import com.careeros.careeros_backend.model.CareerInsight;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CareerInsightRepository
        extends MongoRepository<CareerInsight,String> {

    Optional<CareerInsight> findByEmail(
            String email
    );

    void deleteByEmail(
        String email
);

}

