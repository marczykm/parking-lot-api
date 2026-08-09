package pl.marczyk.parkinglotapi.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record FreeParkingSpotRequest(
        @JsonProperty("vehicleReg") @NotBlank String registration
) {
    public FreeParkingSpotRequest(String registration) {
        this.registration = registration == null ? null : registration.trim().toUpperCase();
    }
}
