package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.ATSAnalysisResponse;
import com.careeros.careeros_backend.service.ATSAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ats")
@RequiredArgsConstructor
@CrossOrigin(
    origins = "http://localhost:5173"
)

public class ATSAnalysisController {

    private final ATSAnalysisService
            atsAnalysisService;

    @PostMapping("/analyze")
    public ATSAnalysisResponse analyzeResume(
            @RequestBody List<String> skills
    ) {

        return atsAnalysisService
                .analyzeResume(skills);
    }
    @GetMapping("/{email}")
public ATSAnalysisResponse
analyzeResumeByEmail(
        @PathVariable String email
) {

    return atsAnalysisService
            .analyzeResume(email);
}
}