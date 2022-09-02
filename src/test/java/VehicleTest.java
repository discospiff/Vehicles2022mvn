import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleTest {

    private Vehicle vehicle;

    @Test
    public void go_increaseOdometerBy100AndDecreasesGallonsOfGasBy5WhenMilesPerGallonIs20(){
        givenVehicleHas0Odometer15GallonsOfGasAnd20MilesPerGallon();
        whenDrive100();
        thenOdometerIncreasesBy100AndGallonsOfGasDecreasesBy5();
    }

    private void givenVehicleHas0Odometer15GallonsOfGasAnd20MilesPerGallon() {
        vehicle = new Vehicle();
        vehicle.setOdometer(0);
        vehicle.setGallonsOfGas(15);
        vehicle.setMilesPerGallon(20);
    }

    private void whenDrive100() {
        vehicle.go(100);
    }

    private void thenOdometerIncreasesBy100AndGallonsOfGasDecreasesBy5() {
        assertEquals(100, vehicle.getOdometer());
        assertEquals(10, vehicle.getGallonsOfGas());
    }

}
