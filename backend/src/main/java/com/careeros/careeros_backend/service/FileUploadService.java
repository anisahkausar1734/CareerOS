package com.careeros.careeros_backend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final Cloudinary cloudinary;

    public String uploadFile(
            MultipartFile file
    ) throws Exception {

        Map<?, ?> uploadResult =
        cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "resource_type",
                        "raw"
                )
        );
        return uploadResult
                .get("secure_url")
                .toString();
    }
}