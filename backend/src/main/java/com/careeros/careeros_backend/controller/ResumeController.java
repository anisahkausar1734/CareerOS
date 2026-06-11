package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.ResumeResponse;
import com.careeros.careeros_backend.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.careeros.careeros_backend.dto.ResumeAnalysisResponse;
import com.careeros.careeros_backend.service.ResumeAnalysisService;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
public class ResumeController {
@PostMapping("/upload")
public ResumeResponse uploadResume(
        @RequestParam String email,
        @RequestParam MultipartFile file
) {

    return resumeService
            .uploadResume(email, file);
} {
}               
    private final ResumeService resumeService;
    private final ResumeAnalysisService resumeAnalysisService;
    @GetMapping("/analyze/{email}")
public ResumeAnalysisResponse analyzeResume(
        @PathVariable String email
) {
    return resumeAnalysisService.analyzeResume(email);
}
@GetMapping("/{email}")
public ResumeResponse getResume(
        @PathVariable String email
) {
    return resumeService.getResume(email);
}
}