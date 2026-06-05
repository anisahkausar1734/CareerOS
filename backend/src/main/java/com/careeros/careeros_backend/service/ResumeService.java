package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.ResumeResponse;
import com.careeros.careeros_backend.dto.UploadResumeRequest;
import com.careeros.careeros_backend.exception.ResumeNotFoundException;
import com.careeros.careeros_backend.model.Resume;
import com.careeros.careeros_backend.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;

    public ResumeResponse uploadResume(
            UploadResumeRequest request
    ) {

        Resume resume = Resume.builder()
                .email(request.getEmail())
                .resumeFileName(request.getResumeFileName())
                .resumeUrl(request.getResumeUrl())
                .uploadedAt(LocalDateTime.now())
                .build();

        Resume savedResume =
                resumeRepository.save(resume);

        return mapToResumeResponse(savedResume);
    }

    public ResumeResponse getResume(String email) {

        Optional<Resume> optionalResume =
                resumeRepository.findByEmail(email);

        if (optionalResume.isEmpty()) {
            throw new ResumeNotFoundException(
                    "Resume not found"
            );
        }

        Resume resume = optionalResume.get();

        return mapToResumeResponse(resume);
    }

    private ResumeResponse mapToResumeResponse(
            Resume resume
    ) {

        return ResumeResponse.builder()
                .email(resume.getEmail())
                .resumeFileName(resume.getResumeFileName())
                .resumeUrl(resume.getResumeUrl())
                .uploadedAt(resume.getUploadedAt())
                .build();
    }
}