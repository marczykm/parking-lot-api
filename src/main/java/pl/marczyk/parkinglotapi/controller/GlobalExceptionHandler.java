package pl.marczyk.parkinglotapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.marczyk.parkinglotapi.controller.dto.ErrorResponse;
import pl.marczyk.parkinglotapi.exception.*;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NoSuchVehicleParkedException.class, VehicleNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFound(Exception ex){
        return status(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler({UnknownVehicleTypeException.class, VehicleAlreadyParkedException.class, NoSpotsLeftException.class})
    public ResponseEntity<ErrorResponse> handleBadRequest(Exception ex){
        return status(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        var message = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return status(HttpStatus.BAD_REQUEST, message);
    }

    private ResponseEntity<ErrorResponse> status(HttpStatus status, String message){
        return ResponseEntity.status(status)
                .body(new ErrorResponse(message, status.value(), LocalDateTime.now()));
    }
}
