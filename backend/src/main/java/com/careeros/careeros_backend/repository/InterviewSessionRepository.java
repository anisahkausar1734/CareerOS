package com.careeros.careeros_backend.repository;

import com.careeros.careeros_backend.model.InterviewSession;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InterviewSessionRepository
        extends MongoRepository<InterviewSession, String> {

    List<InterviewSession>
    findByActiveFalseOrderByEndedAtDesc();
    

}