package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.ResumeResponse;
import com.careeros.careeros_backend.dto.UploadResumeRequest;
import com.careeros.careeros_backend.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
public class ResumeController {
@PostMapping("/upload")
public ResumeResponse uploadResume(
        @RequestBody UploadResumeRequest request
) {
    return resumeService.uploadResume(request);
}               
    private final ResumeService resumeService;
@GetMapping("/{email}")
public ResumeResponse getResume(
        @PathVariable String email
) {
    return resumeService.getResume(email);
}
}