package com.careeros.careeros_backend.dto;

import lombok.Data;

@Data
public class SubmitAnswerRequest {

    private String sessionId;

    private String answer;

}