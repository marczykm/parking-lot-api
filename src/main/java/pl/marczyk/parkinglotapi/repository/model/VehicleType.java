package pl.marczyk.parkinglotapi.repository.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.marczyk.parkinglotapi.exception.UnknownVehicleTypeException;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum VehicleType {
    SMALL(1),
    MEDIUM(2),
    LARGE(3);

    private final Integer value;

    @JsonValue
    public Integer getValue() {
        return value;
    }

    @JsonCreator
    public static VehicleType from(Integer value) {
        return Stream.of(VehicleType.values())
                .filter(type -> Objects.equals(type.value, value))
                .findFirst()
                .orElseThrow(() -> new UnknownVehicleTypeException(value));
    }
}
