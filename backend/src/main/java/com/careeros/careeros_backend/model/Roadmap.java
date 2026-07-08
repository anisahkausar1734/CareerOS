package com.careeros.careeros_backend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "roadmaps")
public class Roadmap {

    @Id
    private String id;

    private String email;

    private String roadmapJson;
}