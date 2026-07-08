package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.ApplicationRequest;
import com.careeros.careeros_backend.model.Application;
import com.careeros.careeros_backend.repository.ApplicationRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public Application addApplication(
            ApplicationRequest request
    ) {

        Application application =
                Application.builder()
                        .email(request.getEmail())
                        .company(request.getCompany())
                        .role(request.getRole())
                        .status(request.getStatus())
                        .notes(request.getNotes())
                        .applicationDate(LocalDate.now())
                        .build();

        return applicationRepository.save(application);
    }

    public List<Application> getApplications(
            String email
    ) {

        return applicationRepository.findByEmail(email);
    }

    public void deleteApplication(
            String id
    ) {

        applicationRepository.deleteById(id);
    }

    public Application updateStatus(
            String id,
            String status
    ) {

        Application application =
                applicationRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Application not found"));

        application.setStatus(status);

        return applicationRepository.save(application);
    }
}