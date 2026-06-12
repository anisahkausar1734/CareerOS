package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.AdminDashboardResponse;
import com.careeros.careeros_backend.service.AdminDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AdminDashboardController {

    private final AdminDashboardService
            adminDashboardService;

    @GetMapping("/dashboard")
    public AdminDashboardResponse
    getDashboard() {

        return adminDashboardService
                .getDashboard();
    }
}
