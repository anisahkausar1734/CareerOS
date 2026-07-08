package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.CareerInsightResponse;

public interface CareerInsightService {

    CareerInsightResponse generateInsight(
            String email
    );

CareerInsightResponse regenerateInsight(
        String email
);

}