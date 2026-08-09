package pl.marczyk.parkinglotapi.service.cost;

import java.math.BigDecimal;

public interface CostRule {

    BigDecimal apply(long minutes);
}
