package pl.marczyk.parkinglotapi.exception;

public class UnknownVehicleTypeException extends RuntimeException {
    public UnknownVehicleTypeException(int vehicleType) {
        super("Unknown vehicle type: " + vehicleType);
    }
}
