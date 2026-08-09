package pl.marczyk.parkinglotapi.repository;

import pl.marczyk.parkinglotapi.repository.model.Vehicle;

import java.util.Optional;

public interface VehicleRepository {

    Optional<Vehicle> findByRegistration(String registration);
    Vehicle save(Vehicle vehicle);
}
