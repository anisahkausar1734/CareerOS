package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.ProjectRecommendationResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectRecommendationService {

    public ProjectRecommendationResponse
    recommendProjects(
            String targetRole
    ) {

        List<String> projects;

        if ("Backend Developer"
                .equalsIgnoreCase(targetRole)) {

            projects = List.of(
                    "URL Shortener",
                    "Job Portal",
                    "Expense Tracker",
                    "E-Commerce Backend",
                    "API Gateway"
            );
        }

        else if ("AI Engineer"
                .equalsIgnoreCase(targetRole)) {

            projects = List.of(
                    "Resume Analyzer",
                    "Chatbot",
                    "AI Interview Coach",
                    "CareerOS AI Mentor"
            );
        }

        else {

            projects = List.of(
                    "Portfolio Website",
                    "Task Manager",
                    "Blog Application"
            );
        }

        return ProjectRecommendationResponse
                .builder()
                .targetRole(targetRole)
                .recommendedProjects(projects)
                .build();
    }
}