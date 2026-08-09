package pl.marczyk.parkinglotapi.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pl.marczyk.parkinglotapi.repository.model.VehicleType;

public record ParkVehicleRequest(
        @JsonProperty("vehicleReg") @NotBlank String registration,
        @JsonProperty("vehicleType") @NotNull VehicleType type) {
}
