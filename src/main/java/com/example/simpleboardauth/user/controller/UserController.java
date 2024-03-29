package com.example.simpleboardauth.user.controller;

import com.example.simpleboardauth.user.dto.LoginRequestDto;
import com.example.simpleboardauth.user.dto.SignupRequestDto;
import com.example.simpleboardauth.user.dto.StringResponseDto;
import com.example.simpleboardauth.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;


    @PostMapping("/user/signup")
    public ResponseEntity<StringResponseDto> signup(@Valid @RequestBody SignupRequestDto requestDto) {
        return ResponseEntity.ok(userService.signup(requestDto));
    }

    @PostMapping("/user/login")
    public ResponseEntity<StringResponseDto> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse res) {
        return ResponseEntity.ok(userService.login(requestDto, res));
    }
}
