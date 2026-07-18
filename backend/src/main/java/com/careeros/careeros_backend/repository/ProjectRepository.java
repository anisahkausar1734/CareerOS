package com.careeros.careeros_backend.repository;

import com.careeros.careeros_backend.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository
        extends MongoRepository<Project, String> {

    List<Project> findByEmail(
            String email
    );

    Optional<Project> findByEmailAndGithubUrl(
            String email,
            String githubUrl
    );

}