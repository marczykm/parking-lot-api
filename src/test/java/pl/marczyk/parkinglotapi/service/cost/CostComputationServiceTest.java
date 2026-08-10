package pl.marczyk.parkinglotapi.service.cost;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.marczyk.parkinglotapi.repository.model.VehicleType;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CostComputationServiceTest {

    private CostComputationService cut;

    @BeforeEach
    void setUp() {
        List<VehicleTypeCostRule> vehicleTypeCostRules = List.of(
                new SmallVehicleCostRule(),
                new MediumVehicleCostRule(),
                new LargeVehicleCostRule()
        );
        List<AdditionalChargeCostRule> additionalChargeRules = List.of(new FiveMinuteCostRule());

        cut = new CostComputationService(vehicleTypeCostRules, additionalChargeRules);
    }

    @ParameterizedTest
    @CsvSource({
            "SMALL, 7, 1.7",   // 7*0.1 + floor(7/5)*1 = 0.7 + 1
            "MEDIUM, 7, 2.4",  // 7*0.2 + 1 = 1.4 + 1
            "LARGE, 7, 3.8",   // 7*0.4 + 1 = 2.8 + 1
            "SMALL, 10, 3.0",  // 10*0.1 + floor(10/5)*1 = 1.0 + 2
            "SMALL, 0, 0.0"
    })
    void should_combine_vehicle_charge_and_five_minute_additional_charge(VehicleType type, long minutes, BigDecimal expected) {
        var actual = cut.compute(type, minutes);

        assertEquals(expected, actual);
    }
}
