package pl.marczyk.parkinglotapi.repository;

import org.springframework.stereotype.Repository;
import pl.marczyk.parkinglotapi.repository.model.Bill;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryBillRepository implements BillRepository {

    private final Map<UUID, Bill> bills = new ConcurrentHashMap<>();
    @Override
    public Optional<Bill> findBy(UUID id) {
        return Optional.ofNullable(bills.get(id));
    }

    @Override
    public Bill save(Bill bill) {
        UUID id = nextId();
        bill.setId(id.toString());
        bills.put(id, bill);
        return bill;
    }

    private UUID nextId() {
        return UUID.randomUUID();
    }
}
