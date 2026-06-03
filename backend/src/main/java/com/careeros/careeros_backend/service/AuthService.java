package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public String testConnection() {
        return "Auth Service Working!";
    }
}