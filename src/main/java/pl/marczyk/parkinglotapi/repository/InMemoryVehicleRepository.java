package pl.marczyk.parkinglotapi.repository;

import org.springframework.stereotype.Repository;
import pl.marczyk.parkinglotapi.repository.model.Vehicle;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryVehicleRepository implements VehicleRepository {

    private final Map<Long, Vehicle> vehicles = new ConcurrentHashMap<>();
    private final AtomicLong id = new AtomicLong(0L);

    @Override
    public Optional<Vehicle> findByRegistrationAndTimeOutNull(String registration) {
        return vehicles.values().stream()
                .filter( vehicle -> vehicle.getRegistration().equals(registration))
                .filter(vehicle -> vehicle.getTimeOut() == null)
                .findFirst();
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        Long id = nextId();
        vehicle.setId(id);
        vehicles.put(id, vehicle);
        return vehicle;
    }

    @Override
    public long countOccupied() {
        return vehicles.values().stream()
                .filter(vehicle -> vehicle.getTimeOut() == null)
                .count();
    }

    public Long nextId() {
        return id.getAndAdd(1L);
    }
}
