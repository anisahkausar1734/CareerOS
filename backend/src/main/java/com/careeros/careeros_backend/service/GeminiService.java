package com.careeros.careeros_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate =
            new RestTemplate();

    public String askGemini(
            String prompt
    ) {

        String url =
                "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="
                        + apiKey;

        Map<String, Object> body =
                Map.of(
                        "contents",
                        List.of(
                                Map.of(
                                        "parts",
                                        List.of(
                                                Map.of(
                                                        "text",
"You are CareerOS AI Mentor. "
+ "Answer only in short bullet points. "
+ "Each point must be on a new line. "
+ "Never use markdown. "
+ "Never use ** symbols. "
+ "Never use headings. "
+ "Keep answers under 8 points. "
+ "Keep answers under 80 words. "
+ "Career-focused answers only.\n\n"
+ prompt
                                                )
                                        )
                                )
                        )
                );

        HttpHeaders headers =
                new HttpHeaders();

        headers.setContentType(
                MediaType.APPLICATION_JSON
        );

        HttpEntity<Map<String, Object>>
                request =
                new HttpEntity<>(
                        body,
                        headers
                );

        ResponseEntity<Map> response =
                restTemplate.postForEntity(
                        url,
                        request,
                        Map.class
                );

        try {

            List candidates =
                    (List)
                            response.getBody()
                                    .get("candidates");

            Map candidate =
                    (Map)
                            candidates.get(0);

            Map content =
                    (Map)
                            candidate.get("content");

            List parts =
                    (List)
                            content.get("parts");

            Map part =
                    (Map)
                            parts.get(0);

            return part.get("text")
                    .toString();

        } catch (Exception e) {

            return "Gemini response parsing failed";
        }
        
    }
public String askGeminiCustom(
        String prompt
) {

    try {

        String url =
                "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="
                        + apiKey;

        Map<String, Object> body =
                Map.of(
                        "contents",
                        List.of(
                                Map.of(
                                        "parts",
                                        List.of(
                                                Map.of(
                                                        "text",
                                                        prompt
                                                )
                                        )
                                )
                        )
                );

        HttpHeaders headers =
                new HttpHeaders();

        headers.setContentType(
                MediaType.APPLICATION_JSON
        );

        HttpEntity<Map<String, Object>>
                request =
                new HttpEntity<>(
                        body,
                        headers
                );

        ResponseEntity<Map> response =
                restTemplate.postForEntity(
                        url,
                        request,
                        Map.class
                );

        List candidates =
                (List)
                        response.getBody()
                                .get("candidates");

        Map candidate =
                (Map)
                        candidates.get(0);

        Map content =
                (Map)
                        candidate.get("content");

        List parts =
                (List)
                        content.get("parts");

        Map part =
                (Map)
                        parts.get(0);

        return part.get("text")
                .toString();

    } catch (Exception e) {

    System.out.println(
            "==================== ERROR ===================="
    );

    System.out.println(
            "Exception Class: "
            + e.getClass().getName()
    );

    System.out.println(
            "Message: "
            + e.getMessage()
    );

    e.printStackTrace();

    System.out.println(
            "================================================"
    );

    throw new RuntimeException(e);
}
}
}