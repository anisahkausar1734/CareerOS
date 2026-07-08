package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.ResumeCenterResponse;
import com.careeros.careeros_backend.service.ResumeCenterService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resume-center")
@RequiredArgsConstructor
public class ResumeCenterController {

    private final ResumeCenterService
            resumeCenterService;

    @GetMapping("/{email}")
    public ResumeCenterResponse getResumeCenter(
            @PathVariable String email
    ) {

        return resumeCenterService
                .getResumeCenter(email);
    }
}