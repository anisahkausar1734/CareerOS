package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.ApplicationRequest;
import com.careeros.careeros_backend.model.Application;
import com.careeros.careeros_backend.service.ApplicationService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public Application addApplication(
            @RequestBody ApplicationRequest request
    ) {

        return applicationService.addApplication(request);
    }

    @GetMapping("/{email}")
    public List<Application> getApplications(
            @PathVariable String email
    ) {

        return applicationService.getApplications(email);
    }

    @PutMapping("/{id}")
    public Application updateStatus(
            @PathVariable String id,
            @RequestParam String status
    ) {

        return applicationService.updateStatus(
                id,
                status
        );
    }

    @DeleteMapping("/{id}")
    public void deleteApplication(
            @PathVariable String id
    ) {

        applicationService.deleteApplication(id);
    }
}