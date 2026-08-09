package pl.marczyk.parkinglotapi.controller.dto;

import java.time.LocalDateTime;

public record FreeParkingSpotResponse(
        String billId,
        String vehicleReg,
        double vehicleCharge,
        LocalDateTime timeIn,
        LocalDateTime timeOut
) {
}
