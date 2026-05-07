package com.project.hotel.exception;

import com.project.hotel.dto.ErrorResponse;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler(HotelNotFoundException.class)

    public ResponseEntity<ErrorResponse>
    handleHotelNotFoundException(

            HotelNotFoundException ex
    ) {

        ErrorResponse error =
                new ErrorResponse(

                        LocalDateTime.now().toString(),

                        HttpStatus.NOT_FOUND.value(),

                        ex.getMessage()
                );

        return new ResponseEntity<>(
                error,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(UserNotFoundException.class)

    public ResponseEntity<ErrorResponse>
    handleUserNotFoundException(

            UserNotFoundException ex
    ) {

        ErrorResponse error =
                new ErrorResponse(

                        LocalDateTime.now().toString(),

                        HttpStatus.NOT_FOUND.value(),

                        ex.getMessage()
                );

        return new ResponseEntity<>(
                error,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(RuntimeException.class)

    public ResponseEntity<ErrorResponse>
    handleRuntimeException(

            RuntimeException ex
    ) {

        ErrorResponse error =
                new ErrorResponse(

                        LocalDateTime.now().toString(),

                        HttpStatus.BAD_REQUEST.value(),

                        ex.getMessage()
                );

        return new ResponseEntity<>(
                error,
                HttpStatus.BAD_REQUEST
        );
    }
}