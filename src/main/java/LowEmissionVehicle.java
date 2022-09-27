/**
 * An interface for low emission vehicles; allows them to apply for a tax credit.
 */
public interface LowEmissionVehicle {

    /**
     * Apply a rebate for driving a low emission vehicle.
     * @param amount
     */
    void applyTaxRebate(int amount);
}
