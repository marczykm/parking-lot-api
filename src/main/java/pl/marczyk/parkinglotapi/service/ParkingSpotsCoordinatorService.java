package pl.marczyk.parkinglotapi.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.marczyk.parkinglotapi.controller.dto.FreeParkingSpotResponse;
import pl.marczyk.parkinglotapi.exception.NoSpotsLeftException;
import pl.marczyk.parkinglotapi.exception.NoSuchVehicleParkedException;
import pl.marczyk.parkinglotapi.exception.VehicleAlreadyParkedException;
import pl.marczyk.parkinglotapi.repository.VehicleRepository;
import pl.marczyk.parkinglotapi.repository.model.Vehicle;
import pl.marczyk.parkinglotapi.repository.model.VehicleType;
import pl.marczyk.parkinglotapi.service.cost.CostComputationService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ParkingSpotsCoordinatorService {

    private final Map<Integer, Optional<Vehicle>> spots = new ConcurrentHashMap<>();
    private final VehicleRepository vehicleRepository;
    private final CostComputationService costComputationService;
    private final int totalSpots;

    public ParkingSpotsCoordinatorService(
            VehicleRepository vehicleRepository,
            CostComputationService costComputationService,
            @Value("${parking.total-spots}") int totalSpots) {
        this.vehicleRepository = vehicleRepository;
        this.costComputationService = costComputationService;
        this.totalSpots = totalSpots;
    }

    @PostConstruct
    private void onInit() {
        for (int i = 1; i <= totalSpots; i++) {
            spots.put(i, Optional.empty());
        }
    }

    public Vehicle park(String vehicleReg, VehicleType vehicleType) {
        Integer spot = findEmptySpot().orElseThrow(NoSpotsLeftException::new);
        vehicleRepository.findByRegistration(vehicleReg)
                .map(Vehicle::getSpot)
                .ifPresent(s -> {
                    throw new VehicleAlreadyParkedException(vehicleReg);
                });
        Vehicle vehicle = vehicleRepository.save(new Vehicle(vehicleReg, vehicleType, spot));

        spots.put(spot, Optional.of(vehicle));
        return vehicle;
    }

    public FreeParkingSpotResponse unpark(String vehicleReg) {
        var timeOut = LocalDateTime.now();
        var vehicle = vehicleRepository.findByRegistration(vehicleReg)
                .orElseThrow(() -> new NoSuchVehicleParkedException(vehicleReg));
        if (vehicle.getSpot() == null) {
            throw new NoSuchVehicleParkedException(vehicleReg);
        }
        var minutesSpent = ChronoUnit.MINUTES.between(vehicle.getTimeIn(), timeOut);
        var bill = costComputationService.compute(vehicle.getType(), minutesSpent);
        spots.put(vehicle.getSpot(), Optional.empty());
        vehicle.setSpot(null);
        vehicleRepository.save(vehicle);
        return new FreeParkingSpotResponse(
                bill.getId(),
                vehicle.getRegistration(), bill.getCost(), vehicle.getTimeIn(), timeOut
        );
    }

    public int getOccupiedSpots() {
        return spots.values().stream().filter(Optional::isPresent).toList().size();
    }

    public int getAvailableSpots() {
        return spots.values().stream().filter(Optional::isEmpty).toList().size();
    }

    private Optional<Integer> findEmptySpot() {
        return spots.entrySet().stream()
                .filter(entry -> entry.getValue().isEmpty())
                .map(Map.Entry::getKey)
                .findFirst();
    }

}
