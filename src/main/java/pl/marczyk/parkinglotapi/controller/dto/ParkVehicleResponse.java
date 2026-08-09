package pl.marczyk.parkinglotapi.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record ParkVehicleResponse(
        @JsonProperty("vehicleReg") String registration,
        Integer spaceNumber,
        LocalDateTime timeIn
) {}
