import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class InventoryReader {

    private static Map<String, Vehicle> allVehicles = new HashMap<>();

    private static Consumer<Vehicle> method = vehicle -> {
        System.out.println(vehicle);
        vehicle.go(100);
        System.out.println(vehicle);};

    public static void main(String[] args) {
        createVehicle();
        runVehicle();
    }

    public static void createVehicle() {

        Path inventoryFilePath = Paths.get("inventory.txt");
        try {

            List<String> inventoryLines = Files.readAllLines(inventoryFilePath);
            for (String inventoryItem: inventoryLines)
            {
                String[] inventoryArray = inventoryItem.split(",");
                if (inventoryArray.length >= 6) {
                    String vin = inventoryArray[0];
                    String carType = inventoryArray[1];
                    String strOdometer = inventoryArray[2];
                    int odometer = Integer.parseInt(strOdometer);
                    String strMilesPerGallon = inventoryArray[3];
                    int milesPerGallon = Integer.parseInt(strMilesPerGallon);
                    String strGallonsOfGas = inventoryArray[4];
                    double gallonsOfGas = Double.parseDouble(strGallonsOfGas);
                    Vehicle vehicle = Driver.getInstance().createVehicle(carType);
                    String description = inventoryArray[5];
                    vehicle.setVin(vin);
                    vehicle.setOdometer(odometer);
                    vehicle.setGallonsOfGas(gallonsOfGas);
                    vehicle.setMilesPerGallon(milesPerGallon);
                    vehicle.setDescription(description);
                    allVehicles.put(vin, vehicle);
                }
            }
        } catch (IOException e) {
            // did something go wrong?  We'll end up here.
            throw new RuntimeException(e);
        }
    }

    /**
     * Fetch a vehicle fromm the collection, by using a unique identifier for the lookup.
     *
     * @param vin the unique identifier used to look up this vehicle.
     * @return the vehicle that matches this VIN.
     */
    public static Vehicle fetchVehicle(String vin) {
        return allVehicles.get(vin);
    }

    private static void runVehicle() {
        allVehicles.values().forEach(method);
    }
}
