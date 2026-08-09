package pl.marczyk.parkinglotapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<ParkVehicleResponse> parkAVehicle(@Validated @RequestBody ParkVehicleRequest request) {
        var vehicle = parkingSpotsCoordinatorService.park(request.vehicleReg(),  request.vehicleType());
        return ResponseEntity.ok(new ParkVehicleResponse(vehicle.getRegistration(), vehicle.getSpot(), vehicle.getTimeIn()));
    }

    @PostMapping("/bill")
    public ResponseEntity<FreeParkingSpotResponse> freeParkingSpot(@Validated @RequestBody FreeParkingSpotRequest request){
        var response = parkingSpotsCoordinatorService.unpark(request.vehicleReg());
        return ResponseEntity.ok(response);
    }
}
