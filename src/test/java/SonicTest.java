public class SonicTest extends VehicleTest {

    protected void givenVehicleHas0Odometer15GallonsOfGasAnd20MilesPerGallon() {
        vehicle = new Sonic();
        vehicle.setOdometer(0);
        vehicle.setGallonsOfGas(15);
        vehicle.setMilesPerGallon(20);
    }
}
