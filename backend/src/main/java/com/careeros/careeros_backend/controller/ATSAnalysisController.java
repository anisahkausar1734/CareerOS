package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.ATSAnalysisResponse;
import com.careeros.careeros_backend.service.ATSAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ats")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ATSAnalysisController {

    private final ATSAnalysisService
            atsAnalysisService;

    @GetMapping("/analyze/{email}")
    public ATSAnalysisResponse analyzeATS(
            @PathVariable
            String email
    ) {

        return atsAnalysisService
                .analyzeResume(email);
    }
}