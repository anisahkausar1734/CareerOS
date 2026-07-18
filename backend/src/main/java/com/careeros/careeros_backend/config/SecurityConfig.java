package com.careeros.careeros_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration =
                new CorsConfiguration();

        configuration.setAllowedOrigins(
                List.of("http://localhost:5173")
        );

        configuration.setAllowedMethods(
                List.of("*")
        );

        configuration.setAllowedHeaders(
                List.of("*")
        );

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration(
                "/**",
                configuration
        );

        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                       .requestMatchers(
        "/api/auth/**",
        "/api/users/**",
        "/api/student-profile/**",

        "/api/projects/**",
        "/api/github/**",

        "/api/career/**",
        "/api/career-analysis/**",
        "/api/career-insight/**",

        "/api/skill-gap/**",
        "/api/roadmap/**",

        "/api/jobs/**",
        "/api/job-match/**",

        "/api/internship/**",
        "/api/internships/**",
        "/api/internship-analysis/**",
        "/api/internship-match/**",

        "/api/resumes/**",
        "/api/resume-analysis/**",
        "/api/resume-center/**",
        "/api/resume-gap/**",
        "/api/resume-refinement/**",
        "/api/refine/**",
        "/api/ats/**",

        "/api/interview/**",

        "/api/resources/**",
        "/api/resources/search/**",

        "/api/progress/**",
        "/api/application-insights/**",
        "/api/applications/**",

        "/api/copilot/**",
        "/api/youtube/**",
        "/api/intelligence/**",

        "/api/admin/**",

        "/api/analyze/**",
        "/api/gemini/**"
)
.permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}