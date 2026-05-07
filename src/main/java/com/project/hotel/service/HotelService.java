package com.project.hotel.service;

import com.project.hotel.dto.HotelResponse;
import com.project.hotel.entity.Hotel;
import com.project.hotel.entity.Room;
import com.project.hotel.exception.HotelNotFoundException;
import com.project.hotel.repository.HotelRepository;
import com.project.hotel.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class HotelService {

    private final HotelRepository hotelRepository;

    private final RoomRepository roomRepository;

    public List<HotelResponse> searchHotels(

            String location,

            String checkIn,

            String checkOut
    ) {

        List<Hotel> hotels =
                hotelRepository
                        .findByLocationContainingIgnoreCase(
                                location
                        );

        return hotels.stream()

                // KEEP ONLY HOTELS
                // HAVING AVAILABLE ROOMS

                .filter(hotel ->

                        roomRepository
                                .countByHotelIdAndAvailableTrue(
                                        hotel.getId()
                                ) > 0
                )

                .map(hotel -> {

                    int availableRooms =
                            roomRepository
                                    .countByHotelIdAndAvailableTrue(
                                            hotel.getId()
                                    );

                    HotelResponse response =
                            new HotelResponse();

                    response.setId(hotel.getId());

                    response.setName(hotel.getName());

                    response.setLocation(hotel.getLocation());

                    response.setTotalRooms(
                            hotel.getTotalRooms()
                    );

                    response.setAvailableRooms(
                            availableRooms
                    );

                    return response;

                }).toList();
    }

    public Hotel getHotelById(Long id) {

        return hotelRepository.findById(id)

                .orElseThrow(() ->
                        new HotelNotFoundException("Hotel Not Found"));
    }

    public List<Room> getRoomsByHotel(Long hotelId) {

        return roomRepository.findByHotelId(hotelId);
    }
}