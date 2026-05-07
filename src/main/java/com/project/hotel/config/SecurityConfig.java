package com.project.hotel.config;

import com.project.hotel.security.CustomUserDetailsService;
import com.project.hotel.security.JwtFilter;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.
        AuthenticationManager;

import org.springframework.security.authentication.
        AuthenticationProvider;

import org.springframework.security.authentication.dao.
        DaoAuthenticationProvider;

import org.springframework.security.config.Customizer;

import org.springframework.security.config.annotation.authentication.configuration.
        AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.
        HttpSecurity;

import org.springframework.security.config.http.
        SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.
        BCryptPasswordEncoder;

import org.springframework.security.crypto.password.
        PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.
        UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor

public class SecurityConfig {

    private final CustomUserDetailsService
            customUserDetailsService;

    private final JwtFilter jwtFilter;

    @Bean

    public SecurityFilterChain securityFilterChain(

            HttpSecurity http

    ) throws Exception {

        return http

                .cors(Customizer.withDefaults())

                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // PUBLIC APIs

                        .requestMatchers(
                                "/auth/**"
                        )


                        .permitAll()
                        .requestMatchers("/setup/**")
                        .hasRole("ADMIN")

                        // USER APIs

                        .requestMatchers(
                                "/bookings/**"
                        )

                        .hasRole("USER")
                        .requestMatchers("/hotels/**")
                        .hasRole("USER")
                        // ALL OTHER APIs

                        .anyRequest()
                        .authenticated()
                )

                .sessionManagement(session ->

                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                // AUTH PROVIDER

                .authenticationProvider(
                        authenticationProvider()
                )

                // JWT FILTER

                .addFilterBefore(

                        jwtFilter,

                        UsernamePasswordAuthenticationFilter.class
                )

                .build();
    }

    @Bean

    public AuthenticationProvider
    authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(customUserDetailsService);


        provider.setPasswordEncoder(
                passwordEncoder()
        );

        return provider;
    }

    @Bean

    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean

    public AuthenticationManager authenticationManager(

            AuthenticationConfiguration configuration
    ) throws Exception {

        return configuration
                .getAuthenticationManager();
    }
}