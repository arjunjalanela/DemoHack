package com.project.hotel.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BookingResponse {

    private Long bookingId;

    private String hotelName;

    private String roomNumber;

    private String roomType;

    private Double price;

    private String checkIn;

    private String checkOut;

    private String status;
}