package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.RoadmapRequest;
import com.careeros.careeros_backend.dto.RoadmapResponse;
import com.careeros.careeros_backend.service.RoadmapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roadmap")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class RoadmapController {

    private final RoadmapService roadmapService;

    @PostMapping
    public RoadmapResponse generateRoadmap(
            @RequestBody RoadmapRequest request
    ) {

        return roadmapService.generateRoadmap(
                request.getTargetRole()
        );
    }
}