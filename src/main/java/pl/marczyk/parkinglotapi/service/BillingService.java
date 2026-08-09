package pl.marczyk.parkinglotapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.marczyk.parkinglotapi.repository.BillRepository;
import pl.marczyk.parkinglotapi.repository.model.Bill;
import pl.marczyk.parkinglotapi.repository.model.VehicleType;
import pl.marczyk.parkinglotapi.service.cost.CostComputationService;

@Service
@RequiredArgsConstructor
public class BillingService {

    private final CostComputationService costComputationService;
    private final BillRepository billRepository;

    public Bill issueBill(VehicleType vehicleType, long minutes) {
        var cost = costComputationService.compute(vehicleType, minutes);
        return billRepository.save(new Bill(cost));
    }

}
