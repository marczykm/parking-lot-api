package pl.marczyk.parkinglotapi.repository.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vehicle {

    final String registration;
    final int type;
    final LocalDateTime timeIn;
    Integer spot;
    Long billId;

    public Vehicle(String registration, int type, int spot) {
        this.registration = registration;
        this.type = type;
        this.spot = spot;
        this.timeIn = LocalDateTime.now();
    }
}
