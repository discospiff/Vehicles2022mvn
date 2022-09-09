public class Prius extends Vehicle {

    public static final int CHARGE_DENOMINATOR = 20;
    public static final int MILES_PER_MILLIAMP_HOUR = 25;
    private int charge;

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public int getCharge() {
        return charge;
    }

    public void go(int milesDriven) {
        if (charge < 5) {
            super.go(milesDriven);
            charge = charge + milesDriven / CHARGE_DENOMINATOR;
        } else if (charge > 5) {
            charge -= milesDriven/ MILES_PER_MILLIAMP_HOUR;
            setOdometer(getOdometer() + milesDriven);
        }
    }

}
