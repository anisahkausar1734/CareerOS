package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.CopilotRequest;
import com.careeros.careeros_backend.dto.CopilotResponse;
import com.careeros.careeros_backend.service.CareerCopilotService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/copilot")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CareerCopilotController {

    private final CareerCopilotService
            careerCopilotService;

    @PostMapping
    public CopilotResponse ask(
            @RequestBody CopilotRequest request
    ) {

        return careerCopilotService.ask(
                request.getEmail(),
                request.getQuestion(),
                request.getHistory()
        );
    }
}