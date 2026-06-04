package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.ProfileResponse;
import com.careeros.careeros_backend.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.careeros.careeros_backend.dto.UpdateProfileRequest;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/profile/{email}")
    public ProfileResponse getProfile(
            @PathVariable String email
    ) {
        return userProfileService.getProfile(email);
    }
@PutMapping("/profile")
public ProfileResponse updateProfile(
        @RequestBody UpdateProfileRequest request
) {
    return userProfileService.updateProfile(request);
}
}