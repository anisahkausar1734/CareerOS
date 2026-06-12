package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.CareerProgressResponse;
import com.careeros.careeros_backend.service.CareerProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CareerProgressController {

    private final CareerProgressService
            careerProgressService;

    @GetMapping("/{email}")
    public CareerProgressResponse
    getProgress(
            @PathVariable String email
    ) {

        return careerProgressService
                .getProgress(email);
    }
}
