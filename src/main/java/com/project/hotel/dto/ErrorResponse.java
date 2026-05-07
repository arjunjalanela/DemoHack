package com.project.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class ErrorResponse {

    private String timestamp;

    private Integer status;

    private String message;
}