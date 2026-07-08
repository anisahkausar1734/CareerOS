package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.ResumeResponse;
import com.careeros.careeros_backend.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping("/upload")
    public ResumeResponse uploadResume(
            @RequestParam String email,
            @RequestParam MultipartFile file
    ) {
        return resumeService.uploadResume(email, file);
    }

    @GetMapping("/{email}")
    public ResumeResponse getResume(
            @PathVariable String email
    ) {
        return resumeService.getResume(email);
    }

    @DeleteMapping("/{email}")
public void deleteResume(
        @PathVariable String email
) {
    resumeService.deleteResume(email);
}

}