package pl.marczyk.parkinglotapi.repository;

import org.springframework.stereotype.Repository;
import pl.marczyk.parkinglotapi.repository.model.Vehicle;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryVehicleRepository implements VehicleRepository {

    private final Map<String, Vehicle> vehicles = new ConcurrentHashMap<>();

    @Override
    public Optional<Vehicle> findByRegistration(String registration) {
        return vehicles.values().stream()
                .filter( vehicle -> vehicle.getRegistration().equals(registration))
                .findFirst();
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        vehicles.put(vehicle.getRegistration(), vehicle);
        return vehicle;
    }
}
