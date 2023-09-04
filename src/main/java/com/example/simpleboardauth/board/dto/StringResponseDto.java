package com.example.simpleboardauth.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StringResponseDto {
    private String msg;
    private String statusCode;

    public StringResponseDto(String msg, String statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
