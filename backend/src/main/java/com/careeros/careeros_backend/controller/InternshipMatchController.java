package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.*;
import com.careeros.careeros_backend.service.InternshipMatchService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        "/api/internship-match"
)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InternshipMatchController {

    private final InternshipMatchService
            internshipMatchService;

   
    @PostMapping("/analyze")
public InternshipMatchResponseDTO analyze(
        @RequestBody
        InternshipMatchRequestDTO request
) {

    System.out.println(request);

    return internshipMatchService
            .analyzeMatch(request);
}
}