package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.InternshipAnalysisRequestDTO;
import com.careeros.careeros_backend.dto.InternshipAnalysisResponseDTO;
import com.careeros.careeros_backend.service.InternshipAnalysisService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
"/api/internship-analysis"
)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InternshipAnalysisController {


private final InternshipAnalysisService
        internshipAnalysisService;

@PostMapping("/analyze")
public ResponseEntity<
        InternshipAnalysisResponseDTO
        > analyze(
        @RequestBody
        InternshipAnalysisRequestDTO request
) {

    return ResponseEntity.ok(
            internshipAnalysisService
                    .analyzeInternshipReadiness(
                            request
                    )
    );
}


}
