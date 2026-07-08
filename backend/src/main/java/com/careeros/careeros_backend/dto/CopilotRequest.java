package com.careeros.careeros_backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class CopilotRequest {

    private String email;

    private String question;

    private List<String> history;
}