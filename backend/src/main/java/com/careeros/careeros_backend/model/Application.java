package com.careeros.careeros_backend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "applications")
public class Application {

    @Id
    private String id;

    private String email;

    private String company;

    private String role;

    private String status;

    private LocalDate applicationDate;

    private String notes;
}