import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Read a data file of buyers; create Buyer objects.
 */
public class BuyerReader {

    public static void readBuyers() {

        Path buyerFilePath = Paths.get("buyers.txt");
        try {

            List<String> buyerLines = Files.readAllLines(buyerFilePath);
            for (String buyerItem: buyerLines)
            {
                String[] buyerArray = buyerItem.split(",");
                if (buyerArray.length >= 4) {
                    String firstName = buyerArray[0];
                    String lastName = buyerArray[1];
                    String driversLicenseNumber = buyerArray[2];
                    String priority = buyerArray[3];
                    Buyer buyer = new Buyer();
                    buyer.setFirstName(firstName);
                    buyer.setLastName(lastName);
                    buyer.setDriversLicenseNumber(driversLicenseNumber);
                    buyer.setPriority(priority);
                }
            }
        } catch (IOException e) {
            // did something go wrong?  We'll end up here.
            throw new RuntimeException(e);
        }
    }


}
