package pl.marczyk.parkinglotapi.controller.dto;

import pl.marczyk.parkinglotapi.repository.model.VehicleType;

public record ParkVehicleRequest(String vehicleReg, VehicleType vehicleType) {
}
