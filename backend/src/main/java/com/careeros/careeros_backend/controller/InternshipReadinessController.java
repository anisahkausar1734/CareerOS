package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.InternshipReadinessResponse;
import com.careeros.careeros_backend.service.InternshipReadinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internship")
@RequiredArgsConstructor
public class InternshipReadinessController {

    private final InternshipReadinessService
            readinessService;

    @GetMapping("/{email}")
    public InternshipReadinessResponse
    getReadiness(
            @PathVariable String email
    ) {

        return readinessService
                .getReadiness(email);
    }
}