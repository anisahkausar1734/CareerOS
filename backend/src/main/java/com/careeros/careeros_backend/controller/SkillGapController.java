package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.SkillGapResponse;
import com.careeros.careeros_backend.service.SkillGapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/career")
@RequiredArgsConstructor
public class SkillGapController {

    private final SkillGapService skillGapService;

    @GetMapping("/skill-gap/{email}")
    public SkillGapResponse getSkillGap(
            @PathVariable String email
    ) {

        return skillGapService.getSkillGap(email);
    }
}