package pl.marczyk.parkinglotapi.service.cost;

import org.springframework.stereotype.Component;

@Component
public class SmallVehicleCostRule implements VehicleTypeCostRule {
    @Override
    public boolean applies(int vehicleType) {
        return vehicleType == 1;
    }

    @Override
    public double apply(long minutes) {
        return minutes * .1;
    }
}
