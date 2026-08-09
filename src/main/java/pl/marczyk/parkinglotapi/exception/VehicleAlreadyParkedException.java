package pl.marczyk.parkinglotapi.exception;

public class VehicleAlreadyParkedException extends RuntimeException {
    public VehicleAlreadyParkedException(String vehicleReg) {
        super("Vehicle with registration " + vehicleReg + " already parked");
    }
}
