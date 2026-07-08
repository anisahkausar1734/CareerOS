package com.careeros.careeros_backend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "resume_refinements")
public class ResumeRefinement {

    @Id
    private String id;

    private String email;

    private String companyName;

    private String jobDescription;

    private String customPrompt;

    private String summary;

    private List<String> atsKeywords;

    private List<String> changesMade;

    private String refinedResume;

    private LocalDateTime createdAt;
}