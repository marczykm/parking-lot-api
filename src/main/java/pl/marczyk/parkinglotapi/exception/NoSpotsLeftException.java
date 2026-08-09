package pl.marczyk.parkinglotapi.exception;

public class NoSpotsLeftException extends RuntimeException {
    public NoSpotsLeftException() {
        super("No empty spots left");
    }
}
