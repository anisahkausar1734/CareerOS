package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.CareerInsightResponse;
import com.careeros.careeros_backend.service.CareerInsightService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/career-insight")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CareerInsightController {

    private final CareerInsightService
            careerInsightService;

    @GetMapping("/{email}")
    public CareerInsightResponse getInsight(
            @PathVariable
            String email
    ) {

        return careerInsightService
                .generateInsight(email);

    }

   @PostMapping("/regenerate/{email}")
public CareerInsightResponse regenerate(
        @PathVariable
        String email
)
{
    return careerInsightService
            .regenerateInsight(
                    email
            );
} 
}