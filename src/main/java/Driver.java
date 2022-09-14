import javax.swing.*;
import java.util.ArrayList;

public class Driver {

    public static final String SONIC = "Sonic";
    public static final String MUSTANG = "Mustang";
    public static final String PRIUS = "Prius";
    private static ArrayList<Vehicle> allVehicles = new ArrayList<>();
//    private static int[] milesPerGallon = {20, 25, 15};
//    private static double[] gallonsOfGas = {10.0, 8, 12};
//    private static int[] odometer  = {0, 10000, 20000};

    public static void main(String[] args) {
        System.out.println("In Vehicles Main");
        promptUser();
        displayOutput();
    }

    public static void promptUser() {
        System.out.println("Second Line");

        int goAgain = JOptionPane.NO_OPTION;
        do {
            String[] availableCars = {SONIC, MUSTANG, PRIUS};
            Object carType = JOptionPane.showInputDialog(null, "Choose a car to create", "Choose a Car", JOptionPane.QUESTION_MESSAGE, null, availableCars, SONIC);

            Vehicle vehicle = createVehicle(carType);
            String strMilesPerGallon = JOptionPane.showInputDialog("Enter Miles per Gallon");
            int milesPerGallon = Integer.parseInt(strMilesPerGallon);
            vehicle.setMilesPerGallon(milesPerGallon);

            String strGallonsOfGas = JOptionPane.showInputDialog("Enter Gallons of Gas");
            double gallonsOfGas = Double.parseDouble(strGallonsOfGas);
            vehicle.setGallonsOfGas(gallonsOfGas);

            String strOdometer = JOptionPane.showInputDialog("Enter Odometer");
            int odometer = Integer.parseInt(strOdometer);
            vehicle.setOdometer(odometer);
            allVehicles.add(vehicle);

            goAgain = JOptionPane.showConfirmDialog(null, "Do you want to enter another vehicle?", "Go Again?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        } while (goAgain == JOptionPane.YES_OPTION);

    }

    private static void displayOutput() {
        do {
            String strMilesDriven = JOptionPane.showInputDialog("How far do you want to go?");
            int milesDriven = Integer.parseInt(strMilesDriven);
            for (Vehicle vehicle : allVehicles) {
                System.out.println(vehicle);
                vehicle.go(milesDriven);
                System.out.println(vehicle);
                Sonic.warrantyNumber = "1-800-462-8782";
            }
        } while (JOptionPane.YES_OPTION  == JOptionPane.showConfirmDialog(null, "Do you want to take another trip?", "Go again?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE));

    }


    /**
     * Simple factory method to create and return a subclass of type Vehicle.
     *
     * @param selectedVehicle A string representing the vehicle we want to create.
     * @return the created vehicle.
     */
    public static Vehicle createVehicle(final Object selectedVehicle) {
        Vehicle vehicle = new Vehicle();
        if (selectedVehicle.toString().equals(SONIC)) {
            vehicle = new Sonic();
        } else if (selectedVehicle.toString().equals(MUSTANG)) {
            vehicle = new Mustang();
        } else if (selectedVehicle.toString().equals(PRIUS)) {
            vehicle = new Prius();
        }
        return vehicle;
    }

}
