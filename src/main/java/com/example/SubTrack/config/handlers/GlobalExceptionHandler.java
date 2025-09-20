package com.example.SubTrack.config.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.example.SubTrack.shared.dtos.ErrorResponseDTO;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<ErrorResponseDTO> handle(ResponseStatusException ex, HttpServletRequest req) {
      ErrorResponseDTO body = ErrorResponseDTO.from(ex.getStatusCode(), ex.getReason(), req.getRequestURI());
      return ResponseEntity.status(ex.getStatusCode()).body(body);
  }
}