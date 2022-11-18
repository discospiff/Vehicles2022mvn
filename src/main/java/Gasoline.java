import java.io.Serializable;

public class Gasoline implements Serializable {
    private double gallons;
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getGallons() {
        return gallons;
    }

    public void setGallons(double gallons) {
        this.gallons = gallons;
    }
}
