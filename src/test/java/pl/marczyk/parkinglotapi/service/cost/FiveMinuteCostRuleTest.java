package pl.marczyk.parkinglotapi.service.cost;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class FiveMinuteCostRuleTest {

    private final FiveMinuteCostRule cut = new FiveMinuteCostRule();

    @ParameterizedTest
    @CsvSource({
            "4, 0",
            "5, 1",
            "6, 1",
            "10, 2"
    })
    void should_apply_one_pound_for_every_five_minute_parking(int minutes, double expected) {
        assertEquals(expected, cut.apply(minutes));
    }
}