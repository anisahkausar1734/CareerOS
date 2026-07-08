package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.DashboardResponseDTO;

public interface DashboardService {

    DashboardResponseDTO getDashboard(String email);

}