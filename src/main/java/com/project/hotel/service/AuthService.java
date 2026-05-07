package com.project.hotel.service;

import com.project.hotel.dto.LoginRequest;
import com.project.hotel.dto.RegisterRequest;
import com.project.hotel.entity.User;
import com.project.hotel.exception.UserNotFoundException;
import com.project.hotel.repository.UserRepository;
import com.project.hotel.security.JwtService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager
            authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public String register(RegisterRequest request) {

        User user = new User();

        user.setName(request.getName());

        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole("ROLE_USER");

        userRepository.save(user);

        return "User Registered Successfully";
    }

    public String login(LoginRequest request) {

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        request.getEmail(),

                        request.getPassword()
                )
        );

        return jwtService.generateToken(
                request.getEmail()
        );
    }
}