package pl.marczyk.parkinglotapi.service.cost;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class FiveMinuteCostRule implements AdditionalChargeCostRule {

    @Override
    public BigDecimal apply(long minutes) {
        return BigDecimal.valueOf(minutes)
                .divide(BigDecimal.valueOf(5), 0, RoundingMode.FLOOR);
    }
}
