package pl.marczyk.parkinglotapi.service.cost;

import org.springframework.stereotype.Component;

@Component
public class MediumVehicleCostRule implements VehicleTypeCostRule {
    @Override
    public boolean applies(int vehicleType) {
        return vehicleType == 2;
    }

    @Override
    public double apply(long minutes) {
        return minutes * .2;
    }
}
