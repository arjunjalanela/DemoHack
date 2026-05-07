package com.project.hotel.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreateHotelRequest {

    private String name;

    private String location;

    private Integer totalRooms;

}