package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.JobListing;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdzunaJobService {

    @Value("${adzuna.app.id}")
    private String appId;

    @Value("${adzuna.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate =
            new RestTemplate();

    public List<JobListing> searchJobs(
            String role,
            String city
    ) {

        try {

            String encodedRole =
                    URLEncoder.encode(
                            role == null ? "" : role,
                            StandardCharsets.UTF_8
                    );

            String encodedCity =
                    URLEncoder.encode(
                            city == null ? "" : city,
                            StandardCharsets.UTF_8
                    );

            String url =
                    "https://api.adzuna.com/v1/api/jobs/in/search/1"
                            + "?app_id=" + appId
                            + "&app_key=" + apiKey
                            + "&results_per_page=20"
                            + "&what=" + encodedRole
                            + "&where=" + encodedCity
                            + "&content-type=application/json";

            ResponseEntity<Map> response =
                    restTemplate.getForEntity(
                            url,
                            Map.class
                    );

            List<JobListing> jobs =
                    new ArrayList<>();

            List<Map<String, Object>> results =
                    (List<Map<String, Object>>)
                            response.getBody()
                                    .get("results");

            if (results == null) {
                return jobs;
            }

            for (Map<String, Object> job : results) {

                Map<String, Object> company =
                        (Map<String, Object>)
                                job.get("company");

                Map<String, Object> location =
                        (Map<String, Object>)
                                job.get("location");


System.out.println(
    "DESCRIPTION = " +
    job.get("description")
);
                                
                jobs.add(
                        JobListing.builder()
                                .title(
                                        String.valueOf(
                                                job.get("title")
                                        )
                                )
                                .company(
                                        company != null
                                                ? String.valueOf(
                                                company.get(
                                                        "display_name"
                                                )
                                        )
                                                : "Unknown"
                                )
                                .location(
                                        location != null
                                                ? String.valueOf(
                                                location.get(
                                                        "display_name"
                                                )
                                        )
                                                : "Unknown"
                                )

.description(
        String.valueOf(
                job.getOrDefault(
                        "description",
                        ""
                )
        )
)
                                
                                .applyLink(
                                        String.valueOf(
                                                job.get(
                                                        "redirect_url"
                                                )
                                        )
                                )
                                .build()
                );
            }

            return jobs;

        } catch (Exception e) {

            e.printStackTrace();

            return List.of();
        }
    }
}