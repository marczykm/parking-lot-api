package pl.marczyk.parkinglotapi.exception;

import pl.marczyk.parkinglotapi.repository.model.VehicleType;

public class UnknownVehicleTypeException extends RuntimeException {
    public UnknownVehicleTypeException(VehicleType vehicleType) {
        super("Unknown vehicle type: " + vehicleType);
    }

    public UnknownVehicleTypeException(Integer vehicleTypeValue) {
        super("Unknown vehicle type: " + vehicleTypeValue);
    }
}
