package com.emma.Task.domain.dto;

public record ErrorResponse(
        int status,
        String message,
        String details) {

}
