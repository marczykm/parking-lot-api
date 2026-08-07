package pl.marczyk.parkinglotapi.repository;

import org.springframework.stereotype.Repository;
import pl.marczyk.parkinglotapi.repository.model.Bill;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryBillRepository implements BillRepository {

    private final Map<Long, Bill> bills = new ConcurrentHashMap<>();
    private final AtomicLong id = new AtomicLong(0L);

    @Override
    public Optional<Bill> findBy(Long id) {
        return Optional.ofNullable(bills.get(id));
    }

    @Override
    public Bill save(Bill bill) {
        Long id = nextId();
        bill.setId(id);
        return bills.put(id, bill);
    }

    private Long nextId() {
        return id.getAndAdd(1L);
    }
}
