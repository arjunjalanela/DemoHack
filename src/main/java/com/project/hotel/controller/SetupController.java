package com.project.hotel.controller;

import com.project.hotel.dto.CreateHotelRequest;

import com.project.hotel.service.SetupService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/setup")

@RequiredArgsConstructor

public class SetupController {

    private final SetupService setupService;

    @PostMapping("/hotel")

    public ResponseEntity<String>
    createHotel(

            @RequestBody CreateHotelRequest request
    ) {

        return ResponseEntity.ok(

                setupService.createHotel(request)
        );
    }
}