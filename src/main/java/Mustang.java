import java.util.ArrayList;
import java.util.List;

public class Mustang extends Vehicle {

    private boolean convertible;

    public void setConvertible(boolean convertible) {
        this.convertible = convertible;
    }

    public boolean isConvertible() {
        return convertible;
    }

    @Override
    protected List<String> getRecommendations() {
        List<String> recommendations = new ArrayList<String>();
        recommendations.add("Oil Change");
        recommendations.add("Tire Rotation");
        return recommendations;
    }

    @Override
    protected int getServiceInterval() {
        return 20000;
    }
}
