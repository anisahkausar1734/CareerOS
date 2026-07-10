package com.careeros.careeros_backend.mapper;

import com.careeros.careeros_backend.dto.ProfileResponse;
import com.careeros.careeros_backend.model.StudentProfile;

public interface ProfileMapper {

    ProfileResponse toResponse(
            StudentProfile profile
    );

}