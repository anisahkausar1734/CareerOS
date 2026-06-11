package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.ResumeResponse;
import com.careeros.careeros_backend.exception.ResumeNotFoundException;
import com.careeros.careeros_backend.model.Resume;
import com.careeros.careeros_backend.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final FileUploadService fileUploadService;

    public ResumeResponse uploadResume(
            String email,
            MultipartFile file
    ) {

        try {

            String uploadedUrl =
                    fileUploadService.uploadFile(file);

            Resume resume =
                    resumeRepository
                            .findByEmail(email)
                            .orElse(
                                    Resume.builder()
                                            .email(email)
                                            .build()
                            );

            resume.setResumeFileName(
                    file.getOriginalFilename()
            );

            resume.setResumeUrl(
                    uploadedUrl
            );

            resume.setUploadedAt(
                    LocalDateTime.now()
            );

            Resume savedResume =
                    resumeRepository.save(resume);

            return mapToResumeResponse(
                    savedResume
            );

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    e.getMessage()
            );
        }
    }

    public ResumeResponse getResume(
            String email
    ) {

        Optional<Resume> optionalResume =
                resumeRepository.findByEmail(email);

        if (optionalResume.isEmpty()) {

            throw new ResumeNotFoundException(
                    "Resume not found"
            );
        }

        Resume resume =
                optionalResume.get();

        return mapToResumeResponse(
                resume
        );
    }

    private ResumeResponse mapToResumeResponse(
            Resume resume
    ) {

        return ResumeResponse.builder()
                .email(resume.getEmail())
                .resumeFileName(
                        resume.getResumeFileName()
                )
                .resumeUrl(
                        resume.getResumeUrl()
                )
                .uploadedAt(
                        resume.getUploadedAt()
                )
                .build();
    }
}