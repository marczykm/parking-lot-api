package pl.marczyk.parkinglotapi.repository;

import pl.marczyk.parkinglotapi.repository.model.Bill;

public interface BillRepository {

    Bill save(Bill bill);

}
