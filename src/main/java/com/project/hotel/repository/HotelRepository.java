package com.project.hotel.repository;

import com.project.hotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByLocationContainingIgnoreCase(String location);
}