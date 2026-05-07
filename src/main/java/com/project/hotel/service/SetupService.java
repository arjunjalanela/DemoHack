package com.project.hotel.service;

import com.project.hotel.dto.CreateHotelRequest;

import com.project.hotel.entity.Hotel;
import com.project.hotel.entity.Room;

import com.project.hotel.repository.HotelRepository;
import com.project.hotel.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class SetupService {

    private final HotelRepository hotelRepository;

    private final RoomRepository roomRepository;

    public String createHotel(
            CreateHotelRequest request
    ) {

        Hotel hotel = new Hotel();

        hotel.setName(request.getName());

        hotel.setLocation(request.getLocation());

        hotel.setTotalRooms(
                request.getTotalRooms()
        );



        // AUTO CREATE ROOMS

        List<Room> rooms = new ArrayList<>();
        for (int i = 1;
             i <= request.getTotalRooms();
             i++) {

            Room room = new Room();

            room.setRoomNumber(
                    String.valueOf(100 + i)
            );

            room.setRoomType("Standard");

            room.setPrice(3000.0);

            room.setAvailable(true);

            room.setHotel(hotel);
            rooms.add(room);
        }
        hotel.setRooms(rooms);
        Hotel savedHotel =
                hotelRepository.save(hotel);
        return "Hotel And Rooms Created Successfully";
    }
}