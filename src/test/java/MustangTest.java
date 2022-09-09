import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MustangTest extends VehicleTest {

    Mustang mustang;


    protected void givenVehicleHas0Odometer15GallonsOfGasAnd20MilesPerGallon() {
        vehicle = new Mustang();
        vehicle.setOdometer(0);
        vehicle.setGallonsOfGas(15);
        vehicle.setMilesPerGallon(20);
    }

    @Test
    public void convertible_WhenConvertibleItIsAConvertible() {
        givenMustangCanBeAConvertible();
        whenMustaingIsAConvertible();
        thenMustangIsAConvertible();

    }

    private void givenMustangCanBeAConvertible() {
        mustang = new Mustang();
    }

    private void whenMustaingIsAConvertible() {
        mustang.setConvertible(true);
    }

    private void thenMustangIsAConvertible() {
        assertTrue(mustang.isConvertible());
    }
}
