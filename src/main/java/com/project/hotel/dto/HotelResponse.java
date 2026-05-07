package com.project.hotel.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class HotelResponse {

    private Long id;

    private String name;

    private String location;

    private Integer totalRooms;

    private Integer availableRooms;
}