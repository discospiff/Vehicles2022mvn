public class Sonic extends Vehicle {
     public static String warrantyNumber = "1 (800) MR GOODWRENCH";

    @Override
    public String toString() {
        return super.toString() + "Warranty Phone Number: " + warrantyNumber;
    }
}
