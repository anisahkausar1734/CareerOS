package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.YouTubeVideoResponse;
import com.careeros.careeros_backend.service.YouTubeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/youtube")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class YouTubeController {

    private final YouTubeService
            youTubeService;

   @GetMapping
public List<YouTubeVideoResponse>
getVideos(
        @RequestParam String skill
)
{
    return youTubeService
            .searchVideos(skill);
}
}