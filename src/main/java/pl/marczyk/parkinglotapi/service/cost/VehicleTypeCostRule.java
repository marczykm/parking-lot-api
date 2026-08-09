package pl.marczyk.parkinglotapi.service.cost;

public interface VehicleTypeCostRule extends CostRule{
    boolean applies(int vehicleType);
}
