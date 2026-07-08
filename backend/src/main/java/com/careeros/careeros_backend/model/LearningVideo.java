package com.careeros.careeros_backend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "learning_videos")
public class LearningVideo {

    @Id
    private String id;

    private String skill;

    private List<String> titles;

    private List<String> channels;

    private List<String> thumbnails;

    private List<String> urls;
}