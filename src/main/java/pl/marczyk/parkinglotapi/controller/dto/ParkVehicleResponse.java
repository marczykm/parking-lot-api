package pl.marczyk.parkinglotapi.controller.dto;

import java.time.LocalDateTime;

public record ParkVehicleResponse(
        String vehicleReg,
        Integer spaceNumber,
        LocalDateTime timeIn
) {}
