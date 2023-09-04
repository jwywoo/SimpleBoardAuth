package com.example.simpleboardauth.user.dto;

import lombok.Getter;

@Getter
public class StringResponseDto {
    String message;
    public StringResponseDto(String message) {
        this.message = message;
    }
}