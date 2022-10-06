import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Driver {

    public final String SONIC = "Sonic";
    public  final String MUSTANG = "Mustang";
    public  final String PRIUS = "Prius";
    private ArrayList<Vehicle> allVehicles = new ArrayList<>();

    private static Driver driver = null;
    
    private Driver() {

    }
    public static void main(String[] args) {
        System.out.println("In Vehicles Main");
        Driver driver = getInstance();
        Driver driver2 = getInstance();
        driver.promptUser();
        driver.displayOutput();
    }

    public static Driver getInstance() {
        if (driver == null) {
            driver = new Driver();
        }
        return driver;
    }

    public void promptUser() {
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

            //is the object inside the vehicle variable of type Prius?
            if (vehicle instanceof Prius) {
                Prius prius = (Prius) vehicle;
                String strCharge = JOptionPane.showInputDialog("What is the battery charge?");
                int charge = Integer.parseInt(strCharge);

                prius.setCharge(charge);

            }



            goAgain = JOptionPane.showConfirmDialog(null, "Do you want to enter another vehicle?", "Go Again?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        } while (goAgain == JOptionPane.YES_OPTION);

    }

    private void displayOutput() {
        do {
            String strMilesDriven = JOptionPane.showInputDialog("How far do you want to go?");
            int milesDriven = Integer.parseInt(strMilesDriven);
            for (Vehicle vehicle : allVehicles) {
                System.out.println(vehicle);
                vehicle.go(milesDriven);
                System.out.println(vehicle);
                List<String> requiredMaintenance = vehicle.checkForRequiredMaintenance();
                for (String maintenance : requiredMaintenance) {
                    JOptionPane.showMessageDialog(null, maintenance, "Suggested Maintenance", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } while (JOptionPane.YES_OPTION  == JOptionPane.showConfirmDialog(null, "Do you want to take another trip?", "Go again?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE));

    }


    /**
     * Simple factory method to create and return a subclass of type Vehicle.
     *
     * @param selectedVehicle A string representing the vehicle we want to create.
     * @return the created vehicle.
     */
    public Vehicle createVehicle(final Object selectedVehicle) {
        Vehicle vehicle = null;
        if (selectedVehicle.toString().equals(SONIC)) {
            vehicle = new Sonic();
        } else if (selectedVehicle.toString().equals(MUSTANG)) {
            Mustang mustang = new Mustang();
            vehicle = mustang;
        } else if (selectedVehicle.toString().equals(PRIUS)) {
            Prius prius = new Prius();
            applyForTaxCredit(prius);
            vehicle = prius;
        }
        return vehicle;
    }

    public void applyForTaxCredit(LowEmissionVehicle lev) {
        lev.applyTaxRebate(100);
    }

}
