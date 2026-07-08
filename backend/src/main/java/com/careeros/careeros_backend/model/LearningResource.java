package com.careeros.careeros_backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "learning_resources")
public class LearningResource {

    @Id
    private String id;

    private String skill;

    private List<String> resources;
}