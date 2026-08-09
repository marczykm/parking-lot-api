package pl.marczyk.parkinglotapi.service.cost;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.marczyk.parkinglotapi.exception.UnknownVehicleTypeException;
import pl.marczyk.parkinglotapi.repository.BillRepository;
import pl.marczyk.parkinglotapi.repository.model.Bill;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CostComputationService {

    private final List<VehicleTypeCostRule> vehicleTypeCostRules;
    private final List<AdditionalChargeCostRule> additionalChargeRules;
    private final BillRepository billRepository;

    public Bill compute(int vehicleType, long minutes) {
        var vehicleTypeCost = vehicleTypeCostRules.stream()
                .filter(rule -> rule.applies(vehicleType))
                .findFirst()
                .map(rule -> rule.apply(minutes))
                .orElseThrow(() -> new UnknownVehicleTypeException(vehicleType));
        var additionalChargeCost = additionalChargeRules.stream()
                .mapToDouble(rule -> rule.apply(minutes))
                .sum();
        var bill = new Bill(vehicleTypeCost + additionalChargeCost);
        bill = billRepository.save(bill);
        return bill;
    }
}
