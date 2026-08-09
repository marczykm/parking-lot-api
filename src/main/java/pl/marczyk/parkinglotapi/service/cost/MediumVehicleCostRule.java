package pl.marczyk.parkinglotapi.service.cost;

import org.springframework.stereotype.Component;
import pl.marczyk.parkinglotapi.repository.model.VehicleType;

import java.math.BigDecimal;

@Component
public class MediumVehicleCostRule implements VehicleTypeCostRule {
    @Override
    public boolean applies(VehicleType vehicleType) {
        return vehicleType == VehicleType.MEDIUM;
    }

    @Override
    public BigDecimal apply(long minutes) {
        return BigDecimal.valueOf(minutes)
                .multiply(BigDecimal.valueOf(.2));
    }
}
