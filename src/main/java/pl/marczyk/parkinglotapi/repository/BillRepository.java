package pl.marczyk.parkinglotapi.repository;

import pl.marczyk.parkinglotapi.repository.model.Bill;

import java.util.Optional;
import java.util.UUID;

public interface BillRepository {

    Optional<Bill> findBy(UUID id);
    Bill save(Bill bill);

}
