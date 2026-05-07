package com.project.hotel.service;

import com.project.hotel.dto.LoginRequest;
import com.project.hotel.dto.RegisterRequest;
import com.project.hotel.entity.User;
import com.project.hotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {

        User user = new User();

        user.setName(request.getName());

        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole("USER");

        userRepository.save(user);

        return "User Registered Successfully";
    }

    public String login(LoginRequest request) {

        return "Login Logic Pending";
    }
}