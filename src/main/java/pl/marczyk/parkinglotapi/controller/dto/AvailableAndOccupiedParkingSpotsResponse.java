package pl.marczyk.parkinglotapi.controller.dto;

public record AvailableAndOccupiedParkingSpotsResponse(
        int availableSpaces,
        int occupiedSpaces
) {
}
