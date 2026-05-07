package com.project.hotel.controller;

import com.project.hotel.dto.HotelResponse;

import com.project.hotel.entity.Hotel;
import com.project.hotel.entity.Room;

import com.project.hotel.service.HotelService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor

public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/search")

    public ResponseEntity<List<HotelResponse>>
    searchHotels(

            @RequestParam String location,

            @RequestParam String checkIn,

            @RequestParam String checkOut
    ) {
        return ResponseEntity.ok(

                hotelService.searchHotels(

                        location,

                        checkIn,

                        checkOut
                )
        );
    }

    @GetMapping("/{id}")

    public ResponseEntity<Hotel>
    getHotelById(

            @PathVariable Long id
    ) {

        return ResponseEntity.ok(

                hotelService.getHotelById(id)
        );
    }

    @GetMapping("/{hotelId}/rooms")

    public ResponseEntity<List<Room>>
    getRoomsByHotel(

            @PathVariable Long hotelId
    ) {

        return ResponseEntity.ok(

                hotelService.getRoomsByHotel(hotelId)
        );
    }
}