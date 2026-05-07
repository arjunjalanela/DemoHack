package com.project.hotel.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String roomNumber;

    private String roomType;

    private Double price;

    private Boolean available;

    @ManyToOne
    @JoinColumn(name = "hotel_id")

    private Hotel hotel;
}