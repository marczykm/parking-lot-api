package pl.marczyk.parkinglotapi.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.marczyk.parkinglotapi.repository.model.VehicleType;

public record ParkVehicleRequest(
        @JsonProperty("vehicleReg") String registration,
        @JsonProperty("vehicleType") VehicleType type) {
}
