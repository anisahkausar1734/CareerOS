package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.ResumeGapResponse;
import com.careeros.careeros_backend.service.ResumeGapService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resume-gap")
@RequiredArgsConstructor
public class ResumeGapController {

    private final ResumeGapService
            resumeGapService;

    @GetMapping("/{email}")
    public ResumeGapResponse analyzeGap(
            @PathVariable String email
    ) {

        return resumeGapService
                .analyzeGap(email);
    }
}