package com.careeros.careeros_backend.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YouTubeVideoResponse {

    private String title;

    private String channel;

    private String thumbnail;

    private String videoUrl;
}