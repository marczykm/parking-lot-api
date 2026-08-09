package pl.marczyk.parkinglotapi.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FreeParkingSpotResponse(
        String billId,
        String vehicleReg,
        BigDecimal vehicleCharge,
        LocalDateTime timeIn,
        LocalDateTime timeOut
) {
}
