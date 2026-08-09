package pl.marczyk.parkinglotapi.service.cost;

import pl.marczyk.parkinglotapi.repository.model.VehicleType;

public interface VehicleTypeCostRule extends CostRule{
    boolean applies(VehicleType vehicleType);
}
