package com.project.hotel.service;

import com.project.hotel.dto.BookingRequest;

import com.project.hotel.entity.Booking;
import com.project.hotel.entity.Room;
import com.project.hotel.entity.User;

import com.project.hotel.exception.HotelNotFoundException;
import com.project.hotel.exception.UserNotFoundException;
import com.project.hotel.repository.BookingRepository;
import com.project.hotel.repository.RoomRepository;
import com.project.hotel.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.
        SecurityContextHolder;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor

public class BookingService {

    private final BookingRepository bookingRepository;

    private final RoomRepository roomRepository;

    private final UserRepository userRepository;

    public String bookRoom(BookingRequest request) {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User Not Found"
                        ));

        Room room = roomRepository
                .findById(request.getRoomId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Room Not Found"
                        ));

        // CHECK ROOM AVAILABILITY

        if (!room.getAvailable()) {

            throw new RuntimeException(
                    "Room Already Booked"
            );
        }

        Booking booking = new Booking();

        booking.setUser(user);

        booking.setRoom(room);

        booking.setCheckIn(
                LocalDate.parse(request.getCheckIn())
        );

        booking.setCheckOut(
                LocalDate.parse(request.getCheckOut())
        );

        booking.setStatus("BOOKED");

        // MARK ROOM UNAVAILABLE

        room.setAvailable(false);

        roomRepository.save(room);

        bookingRepository.save(booking);

        return "Room Booked Successfully";
    }

    public List<Booking> myBookings() {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException("User Not Found"));

        return bookingRepository.findByUserId(user.getId());
    }

    public String cancelBooking(Long bookingId) {

        Booking booking = bookingRepository
                .findById(bookingId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Booking Not Found"
                        ));

        booking.setStatus("CANCELLED");

        // MAKE ROOM AVAILABLE AGAIN

        Room room = booking.getRoom();

        room.setAvailable(true);

        roomRepository.save(room);

        bookingRepository.save(booking);

        return "Booking Cancelled Successfully";
    }
}