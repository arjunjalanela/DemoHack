package com.project.hotel.controller;

import com.project.hotel.dto.BookingRequest;

import com.project.hotel.dto.BookingResponse;
import com.project.hotel.entity.Booking;

import com.project.hotel.service.BookingService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor

public class BookingController {

    private final BookingService bookingService;

    @PostMapping

    public ResponseEntity<String>
    bookRoom(

            @RequestBody BookingRequest request
    ) {

        return ResponseEntity.ok(

                bookingService.bookRoom(request)
        );
    }

    @GetMapping("/my")

    public ResponseEntity<List<BookingResponse>>
    myBookings() {

        return ResponseEntity.ok(

                bookingService.myBookings()
        );
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<String>
    cancelBooking(

            @PathVariable Long id
    ) {

        return ResponseEntity.ok(

                bookingService.cancelBooking(id)
        );
    }
}