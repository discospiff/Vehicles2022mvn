import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriusTest {

    private Prius prius;

    @Test
    public void go_runningWithGas() {
        givenGallonsOfGas10Odometer0MilesPerGallon50Charge4();
        whenDriven100Miles();
        thenGallonsOfGas8Odometer100MilliampHours9();
    }

    private void givenGallonsOfGas10Odometer0MilesPerGallon50Charge4() {
        prius = new Prius();
        prius.setGallonsOfGas(10);
        prius.setOdometer(0);
        prius.setMilesPerGallon(50);
        prius.setCharge(4);
    }

    private void whenDriven100Miles() {
        prius.go(100);
    }


    private void thenGallonsOfGas8Odometer100MilliampHours9() {
        assertEquals(8, prius.getGallonsOfGas());
        assertEquals(100, prius.getOdometer());
        assertEquals(9, prius.getCharge());
    }

    @Test
    public void go_runningWithElectricity() {
        givenGallonsOfGas10Odometer100MilliampHours9();
        whenDriven100Miles();
        thenGallonsOfGas10Odometer200Charge5();
    }

    private void givenGallonsOfGas10Odometer100MilliampHours9() {
        prius = new Prius();
        prius.setGallonsOfGas(10);
        prius.setOdometer(100);
        prius.setMilesPerGallon(50);
        prius.setCharge(9);
    }

    private void thenGallonsOfGas10Odometer200Charge5() {
        assertEquals(10, prius.getGallonsOfGas());
        assertEquals(200, prius.getOdometer());
        assertEquals(5, prius.getCharge());
    }
}
