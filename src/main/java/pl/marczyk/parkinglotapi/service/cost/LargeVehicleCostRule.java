package pl.marczyk.parkinglotapi.service.cost;

import org.springframework.stereotype.Component;

@Component
public class LargeVehicleCostRule implements VehicleTypeCostRule {
    @Override
    public boolean applies(int vehicleType) {
        return vehicleType == 3;
    }

    @Override
    public double apply(long minutes) {
        return minutes * .4;
    }
}
