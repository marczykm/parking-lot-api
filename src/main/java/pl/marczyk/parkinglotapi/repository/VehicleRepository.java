package pl.marczyk.parkinglotapi.repository;

import pl.marczyk.parkinglotapi.repository.model.Vehicle;

import java.util.Optional;

public interface VehicleRepository {

    Optional<Vehicle> findByRegistrationAndTimeOutNull(String registration);
    Vehicle save(Vehicle vehicle);
    long countOccupied();
}
