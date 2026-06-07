package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.RecommendedSkill;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {

    public List<RecommendedSkill> recommendSkills(
            List<String> missingSkills
    ) {

        List<RecommendedSkill> recommendations =
                new ArrayList<>();

        int priority = 1;

        for (String skill : missingSkills) {

            recommendations.add(
                    RecommendedSkill.builder()
                            .skillName(skill)
                            .priority(priority++)
                            .reason(
                                    getReason(skill)
                            )
                            .build()
            );
        }

        return recommendations;
    }

    private String getReason(String skill) {

        switch (skill) {

            case "SQL":
                return "Required by almost every backend role";

            case "Git":
                return "Industry standard version control";

            case "Docker":
                return "Used for application deployment";

            case "Redis":
                return "Important for caching and scalability";

            case "AWS":
                return "Most demanded cloud platform";

            default:
                return "Important for your target role";
        }
    }
}
