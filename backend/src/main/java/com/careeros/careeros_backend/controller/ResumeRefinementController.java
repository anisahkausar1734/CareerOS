package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.ResumeRefinementRequestDTO;
import com.careeros.careeros_backend.dto.ResumeRefinementResponseDTO;
import com.careeros.careeros_backend.service.ResumeRefinementService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        "/api/resume-refinement"
)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ResumeRefinementController {

    private final ResumeRefinementService
            resumeRefinementService;

    @PostMapping("/generate")
    public ResponseEntity<
            ResumeRefinementResponseDTO
            > generateResume(
            @RequestBody
            ResumeRefinementRequestDTO request
    ) {

        return ResponseEntity.ok(
                resumeRefinementService
                        .refineResume(
                                request
                        )
        );
    }

    @GetMapping("/history/{email}")
public ResponseEntity<?>
getHistory(
        @PathVariable
        String email
) {

    return ResponseEntity.ok(
            resumeRefinementService
                    .getHistory(
                            email
                    )
    );
}

@GetMapping("/history/version/{id}")
public ResponseEntity<?>
getVersion(
        @PathVariable
        String id
) {

    return ResponseEntity.ok(
            resumeRefinementService
                    .getVersion(
                            id
                    )
    );
}

}