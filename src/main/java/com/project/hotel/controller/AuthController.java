package com.project.hotel.controller;

import com.project.hotel.dto.LoginRequest;
import com.project.hotel.dto.RegisterRequest;
import com.project.hotel.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/auth")

@RequiredArgsConstructor

public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")

    public String register(
            @RequestBody RegisterRequest request
    ) {

        return authService.register(request);
    }

    @PostMapping("/login")

    public String login(
            @RequestBody LoginRequest request
    ) {

        return authService.login(request);
    }
}