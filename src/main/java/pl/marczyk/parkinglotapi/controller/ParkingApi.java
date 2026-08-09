package pl.marczyk.parkinglotapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.marczyk.parkinglotapi.controller.dto.*;
import pl.marczyk.parkinglotapi.service.ParkingSpotsCoordinatorService;

@RestController
@RequestMapping("/api/v1/parking")
@RequiredArgsConstructor
public class ParkingApi {

    private final ParkingSpotsCoordinatorService parkingSpotsCoordinatorService;

    @GetMapping
    public ResponseEntity<AvailableAndOccupiedParkingSpotsResponse> getAvailableAndOccupiedParkingSpots() {
        var occupiedSpots = parkingSpotsCoordinatorService.getOccupiedSpots();
        var availableSpots = parkingSpotsCoordinatorService.getAvailableSpots();
        return ResponseEntity.ok(new AvailableAndOccupiedParkingSpotsResponse(availableSpots, occupiedSpots));
    }

    @PostMapping
    public ResponseEntity<ParkVehicleResponse> parkAVehicle(@Valid @RequestBody ParkVehicleRequest request) {
        var vehicle = parkingSpotsCoordinatorService.park(request.registration(),  request.type());
        return ResponseEntity.ok(new ParkVehicleResponse(vehicle.getRegistration(), vehicle.getSpot(), vehicle.getTimeIn()));
    }

    @PostMapping("/bill")
    public ResponseEntity<FreeParkingSpotResponse> freeParkingSpot(@Valid @RequestBody FreeParkingSpotRequest request){
        var response = parkingSpotsCoordinatorService.unpark(request.registration());
        return ResponseEntity.ok(response);
    }
}
