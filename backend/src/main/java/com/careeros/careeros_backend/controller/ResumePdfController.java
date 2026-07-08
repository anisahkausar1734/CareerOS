package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.ResumePdfRequestDTO;
import com.careeros.careeros_backend.service.ResumePdfService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resume-pdf")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ResumePdfController {

    private final ResumePdfService
            resumePdfService;

    @PostMapping("/download")
    public ResponseEntity<byte[]>
    downloadPdf(
            @RequestBody
            ResumePdfRequestDTO request
    ) {

        byte[] pdf =
                resumePdfService
                        .generatePdf(
                                request
                        );

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=refined_resume.pdf"
                )
                .contentType(
                        MediaType.APPLICATION_PDF
                )
                .body(pdf);
    }
}