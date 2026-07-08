package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.ResumeAnalysisResponse;
import com.careeros.careeros_backend.service.ResumeAnalysisService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resume-analysis")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ResumeAnalysisController {

    private final ResumeAnalysisService
            resumeAnalysisService;

    @GetMapping("/{email}")
    public ResumeAnalysisResponse getResumeAnalysis(
            @PathVariable String email
    ) {

        return resumeAnalysisService
                .analyzeResume(email);
    }

    @PostMapping("/reanalyze/{email}")
public ResumeAnalysisResponse reanalyze(
        @PathVariable String email
) {

    return resumeAnalysisService
            .reanalyzeResume(email);
}

@GetMapping("/cached/{email}")
public ResumeAnalysisResponse getCachedAnalysis(
        @PathVariable String email
) {

    return resumeAnalysisService
            .getCachedAnalysis(email);
}

}