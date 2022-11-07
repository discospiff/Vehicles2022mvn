import java.util.HashMap;
import java.util.Map;

/**
 * Represents a person who wants to acquire a vehicle.
 */
public class Buyer implements Comparable<Buyer> {
    private String firstName;
    private String lastName;
    private String driversLicenseNumber;
    private String priority;

    private static Map<String, Integer> priorities = new HashMap<>();

    static {
        priorities.put("Diamond", 1);
        priorities.put("Platinum", 2);
        priorities.put("Gold", 3);
        priorities.put("Silver", 4);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDriversLicenseNumber() {
        return driversLicenseNumber;
    }

    public void setDriversLicenseNumber(String driversLicenseNumber) {
        this.driversLicenseNumber = driversLicenseNumber;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Buyer o) {
        int ourPriority = priorities.get(getPriority());
        int theirPriority = priorities.get(o.getPriority());
        return ourPriority - theirPriority;
    }
}
