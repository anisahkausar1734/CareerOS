package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.StudentProfileRequest;
import com.careeros.careeros_backend.dto.StudentProfileResponse;
import com.careeros.careeros_backend.service.StudentProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student-profile")
@RequiredArgsConstructor
public class StudentProfileController {

    private final StudentProfileService
            studentProfileService;

    @PostMapping
    public StudentProfileResponse saveProfile(
            @RequestBody
            StudentProfileRequest request
    ) {

        return studentProfileService
                .saveProfile(
                        request
                );
    }

    @GetMapping("/{email}")
    public StudentProfileResponse getProfile(
            @PathVariable
            String email
    ) {

        return studentProfileService
                .getProfile(
                        email
                );
    }

    @PutMapping("/{email}")
public StudentProfileResponse updateProfile(
        @PathVariable String email,
        @RequestBody StudentProfileRequest request
) {

    return studentProfileService
            .updateProfile(
                    email,
                    request
            );
}
}