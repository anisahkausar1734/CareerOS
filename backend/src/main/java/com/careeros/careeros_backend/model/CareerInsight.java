package com.careeros.careeros_backend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "career_insights")
public class CareerInsight {

    @Id
    private String id;

    private String email;

    private String insightJson;

    private LocalDateTime createdAt;

}