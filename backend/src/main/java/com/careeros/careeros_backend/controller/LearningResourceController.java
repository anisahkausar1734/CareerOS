package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.LearningResourceResponse;
import com.careeros.careeros_backend.service.LearningResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
@CrossOrigin(
        origins = "http://localhost:5173"
)
public class LearningResourceController {

    private final LearningResourceService
            learningResourceService;

    @GetMapping("/{email}")
    public LearningResourceResponse
    getResources(
            @PathVariable String email
    ) {

        return learningResourceService
                .getResources(email);
    }
}