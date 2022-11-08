import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class Vehicle {


    public Vehicle() {
        int i = 1 + 1;
    }
    public Vehicle(int foo) {
        int i = 1 + 1;
    }
    private int milesPerGallon = 20;
    private double gallonsOfGas = 10.0;
    protected int odometer  = 0;

    protected String vin;

    private Buyer buyer;

    private Stack<Gasoline> gasoline = new Stack<>();

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected String description;

    protected int previousOdometer = 0;

    private String color;

    public int getMilesPerGallon() {
        return milesPerGallon;
    }

    public void setMilesPerGallon(int milesPerGallon) {
        this.milesPerGallon = milesPerGallon;
    }

    public double getGallonsOfGas() {
        return gallonsOfGas;
    }

    public void setGallonsOfGas(double gallonsOfGas) {
        this.gallonsOfGas = gallonsOfGas;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public void go(int milesDriven) {
        previousOdometer = getOdometer();
        double gallonsConsumed = milesDriven / getMilesPerGallon();
        setGallonsOfGas(getGallonsOfGas() - (gallonsConsumed));
        setOdometer(getOdometer() + milesDriven);
        if (!gasoline.isEmpty()) {
            do {
                Gasoline gas = gasoline.peek();
                double gallonsInPeek = gas.getGallons();
                if (gallonsConsumed == gallonsInPeek) {
                    gasoline.pop();
                    gallonsConsumed = 0;
                } else if (gallonsConsumed < gallonsInPeek) {
                    gas.setGallons(gas.getGallons() - gallonsConsumed);
                    gallonsConsumed = 0;
                } else if (gallonsConsumed > gallonsInPeek) {
                    gallonsConsumed -= gallonsInPeek;
                    gasoline.pop();
                }
            } while (gallonsConsumed > 0);
        }
    }

    private double computeTotalGasValue() {
        double value = 0;
        for (Gasoline gas: gasoline)
        {
            value += gas.getGallons() * gas.getPrice();
        }

        return value;
    }

    private double computeAverageGasValue() {
        double value = 0;
        double gallons = 0;
        for (Gasoline gas: gasoline)
        {
            value += gas.getGallons() * gas.getPrice();
            gallons += gas.getGallons();
        }
        return value/gallons;
    }

    @Override
    public String toString() {
        return " Odometer " + getOdometer() + " Gallons of Gas " + getGallonsOfGas() + " Average cost per gallon: " + computeAverageGasValue() + " Total cost per gallon: " + computeTotalGasValue();
    }

    public List<String> checkForRequiredMaintenance() {
        List<String> requiredMaintenance = new ArrayList<String>();
        int serviceInterval = getServiceInterval();
        if (serviceInterval >= previousOdometer && serviceInterval <= odometer) {
            requiredMaintenance = getRecommendations();
        }

        return requiredMaintenance;
    }

    protected abstract List<String> getRecommendations();

    protected abstract int getServiceInterval();

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Stack<Gasoline> getGasoline() {
        return gasoline;
    }

    public void setGasoline(Stack<Gasoline> gasoline) {
        this.gasoline = gasoline;
    }
}
