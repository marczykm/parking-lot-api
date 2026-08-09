package pl.marczyk.parkinglotapi.service.cost;

import org.springframework.stereotype.Component;
import pl.marczyk.parkinglotapi.repository.model.VehicleType;

@Component
public class LargeVehicleCostRule implements VehicleTypeCostRule {
    @Override
    public boolean applies(VehicleType vehicleType) {
        return vehicleType == VehicleType.LARGE;
    }

    @Override
    public double apply(long minutes) {
        return minutes * .4;
    }
}
