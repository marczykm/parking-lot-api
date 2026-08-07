package pl.marczyk.parkinglotapi.repository.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vehicle {

    Long id;
    String registration;
    int type;
    LocalDateTime timeIn;
    LocalDateTime timeOut;
    Long billId;
}
