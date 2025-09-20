package com.example.SubTrack.shared.dtos;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public class ErrorResponseDTO {
    private final int status;
    private final String error;
    private final String message;
    private final String path;
    private final LocalDateTime timestamp = LocalDateTime.now();

    private ErrorResponseDTO(int status, String error, String message, String path) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public static ErrorResponseDTO from(HttpStatusCode statusCode, String message, String path) {
        int code = statusCode.value();
        String reason;
        if (statusCode instanceof HttpStatus hs) {
            reason = hs.getReasonPhrase();
        } else {
            // fallback: mapeia pelo código
            reason = HttpStatus.valueOf(code).getReasonPhrase();
        }
        return new ErrorResponseDTO(code, reason, message, path);
    }
}
