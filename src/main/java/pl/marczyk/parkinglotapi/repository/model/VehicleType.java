package pl.marczyk.parkinglotapi.repository.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VehicleType {
    SMALL(1),
    MEDIUM(2),
    LARGE(3);

    private final Integer value;
}
