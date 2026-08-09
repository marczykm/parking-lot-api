package pl.marczyk.parkinglotapi.exception;

public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException(String vehicleReg) {
        super("Vehicle with registration " + vehicleReg + " not found");
    }
}
