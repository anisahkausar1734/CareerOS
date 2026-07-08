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
                                "/api/career/**",
                                "/api/projects/**",
                                "/api/internship/**",
                                "/api/gemini/**",
                                "/api/roadmap/**",
                                "/api/interview/**",
                                "/api/jobs/**",
                                "/api/resources/**",
                                "/api/progress/**",
                                "/api/admin/**",
                                "/api/job-match/**",
                                "/api/applications/**",
                                "/api/application-insights/**",
                                "/api/copilot/**",
                                "/api/interview/**",
                                "/api/student-profile/**",
                                "/api/skill-gap/**",
                                "/api/ats/**",
                                "/api/resumes/**",
                                "/api/refine/**",
                                "/api/resume-center/**",
                                "/api/resume-gap/**",
                                "/api/resume-refinement/**",
                                "/api/internship-analysis/**",
                                "/api/internships/**",
                                "/api/internship-match/**", 
                                "/api/github/**",
                                "/api/job-readiness/**",
                                "/api/internship/**",
                                "/api/career-analysis/**",
                                "/api/analyze/**",
                                "/api/resume-analysis/**",
                                "/api/youtube/**",
                                "/api/intelligence/**",
                                "/api/projects/intelligence/**",
                                "/api/career-insight/**",
                                "/api/projects/blueprint/**",
                                "/api/resources/search/**"
   

                        )
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}