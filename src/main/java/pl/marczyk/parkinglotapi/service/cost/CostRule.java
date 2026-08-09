package pl.marczyk.parkinglotapi.service.cost;

public interface CostRule {

    double apply(long minutes);
}
