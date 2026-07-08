package com.careeros.careeros_backend.repository;

import com.careeros.careeros_backend.model.ProjectBlueprint;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProjectBlueprintRepository
        extends MongoRepository<ProjectBlueprint,String> {

    Optional<ProjectBlueprint>
    findByProjectName(
            String projectName
    );

    void deleteByProjectName(
            String projectName
    );

}