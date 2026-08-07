package pl.marczyk.parkinglotapi.repository;

import pl.marczyk.parkinglotapi.repository.model.Bill;

import java.util.Optional;

public interface BillRepository {

    Optional<Bill> findBy(Long id);
    Bill save(Bill bill);

}
