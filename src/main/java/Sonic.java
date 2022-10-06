import java.util.ArrayList;
import java.util.List;

public class Sonic extends Vehicle {
     public static String warrantyNumber = "1 (800) MR GOODWRENCH";

    @Override
    public String toString() {
        return super.toString() + "Warranty Phone Number: " + warrantyNumber;
    }

    @Override
    protected List<String> getRecommendations() {
        List<String> recommendations = new ArrayList<String>();
        recommendations.add("Oil Change");
        recommendations.add("Tire Rotation");
        recommendations.add("Spark Plugs");
        return recommendations;
    }

    @Override
    protected int getServiceInterval() {
        return 25000;
    }
}
