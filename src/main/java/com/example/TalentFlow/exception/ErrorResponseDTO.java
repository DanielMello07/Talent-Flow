package com.example.TalentFlow.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDTO {
    private String message;
    private int status;
    private String timestamp;
    private String path;

    public ErrorResponseDTO(String message, int status, String timestamp, String path) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
        this.path = path;
    }
}