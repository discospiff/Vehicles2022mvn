import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Read a data file of buyers; create Buyer objects.
 */
public class BuyerReader {

    private static Queue<Buyer> buyers = new PriorityQueue<>();

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
                    buyers.offer(buyer);
                }
            }
        } catch (IOException e) {
            // did something go wrong?  We'll end up here.
            throw new RuntimeException(e);
        }
    }

    public static Buyer fetchNextQualifiedBuyer() {
        return buyers.peek();
    }

    public static void removeBuyer(Buyer inBuyer) throws Exception {
        Buyer nextBuyer = buyers.poll();
        if (!nextBuyer.equals(inBuyer)) {
            throw new Exception ("Buyer is not in queue");
        }
    }

}
