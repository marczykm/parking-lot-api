package pl.marczyk.parkinglotapi.service.cost;

import org.springframework.stereotype.Component;

@Component
public class FiveMinuteCostRule implements AdditionalChargeCostRule {

    @Override
    public double apply(long minutes) {
        return Math.ceil(minutes / 5d);
    }
}
