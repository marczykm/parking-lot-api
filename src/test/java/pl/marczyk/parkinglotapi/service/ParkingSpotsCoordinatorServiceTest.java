package pl.marczyk.parkinglotapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import pl.marczyk.parkinglotapi.exception.NoSpotsLeftException;
import pl.marczyk.parkinglotapi.exception.NoSuchVehicleParkedException;
import pl.marczyk.parkinglotapi.exception.VehicleAlreadyParkedException;
import pl.marczyk.parkinglotapi.repository.InMemoryVehicleRepository;
import pl.marczyk.parkinglotapi.repository.VehicleRepository;
import pl.marczyk.parkinglotapi.repository.model.Bill;
import pl.marczyk.parkinglotapi.repository.model.VehicleType;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ParkingSpotsCoordinatorServiceTest {

    private static final int TOTAL_SPOTS = 2;

    private VehicleRepository vehicleRepository;
    private BillingService billingService;
    private ParkingSpotsCoordinatorService cut;

    @BeforeEach
    void setUp() {
        vehicleRepository = new InMemoryVehicleRepository();
        billingService = mock(BillingService.class);
        cut = new ParkingSpotsCoordinatorService(vehicleRepository, billingService, TOTAL_SPOTS);
        ReflectionTestUtils.invokeMethod(cut, "onInit");
    }

    @Test
    void should_park_vehicle_in_first_available_spot() {
        // given
        var registration = "AB123";

        // when
        var vehicle = cut.park(registration, VehicleType.SMALL);

        // then
        assertEquals(registration, vehicle.getRegistration());
        assertEquals(1, vehicle.getSpot());
        assertEquals(1, cut.getOccupiedSpots());
        assertEquals(TOTAL_SPOTS - 1, cut.getAvailableSpots());
    }

    @Test
    void should_throw_when_same_registration_parked_twice() {
        // given
        var registration = "AB123";

        // when
        cut.park(registration, VehicleType.SMALL);

        //then
        assertThrows(VehicleAlreadyParkedException.class,
                () -> cut.park(registration, VehicleType.MEDIUM));
    }

    @Test
    void should_throw_when_no_spots_left() {
        // given
        var registration1 = "AB111";
        var registration2 = "AB222";
        var registration3 = "AB333";

        cut.park(registration1, VehicleType.SMALL);
        cut.park(registration2, VehicleType.SMALL);

        // when then
        assertThrows(NoSpotsLeftException.class,
                () -> cut.park(registration3, VehicleType.SMALL));
    }

    @Test
    void should_free_spot_and_return_bill_on_unpark() {
        // given
        var registration = "AB123";
        var bill = new Bill(BigDecimal.valueOf(2));
        when(billingService.issueBill(any(), anyLong())).thenReturn(bill);
        cut.park(registration, VehicleType.SMALL);

        // when
        var response = cut.unpark(registration);

        // then
        assertEquals(registration, response.vehicleReg());
        assertEquals(bill.getCost(), response.vehicleCharge());
        assertEquals(0, cut.getOccupiedSpots());
        assertEquals(TOTAL_SPOTS, cut.getAvailableSpots());
    }

    @Test
    void should_throw_when_unparking_already_unparked_vehicle() {
        // given
        var registration = "AB123";
        when(billingService.issueBill(any(), anyLong())).thenReturn(new Bill(BigDecimal.ZERO));
        cut.park(registration, VehicleType.SMALL);
        cut.unpark(registration);

        // when then
        assertThrows(NoSuchVehicleParkedException.class, () -> cut.unpark(registration));
    }
}
