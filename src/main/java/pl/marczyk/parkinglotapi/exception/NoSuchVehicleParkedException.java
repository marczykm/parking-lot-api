package pl.marczyk.parkinglotapi.exception;

public class NoSuchVehicleParkedException extends RuntimeException {
    public NoSuchVehicleParkedException(String vehicleReg) {
        super("Vehicle with registration " + vehicleReg + " not parked");
    }
}
