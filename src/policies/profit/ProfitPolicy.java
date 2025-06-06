package policies.profit;

/**
 *
 * @author gravlax,hugues
 * An interface to represent any kind of ProfitPolicy for MyFoodora
 * system, which can modulate the serviceFee, markupPercentage and DeliveryCost.
 *
 */
public interface ProfitPolicy {
    double computeVariable(double targetProfit, double totalIncome, int nbOrders, double serviceFee, double markupPercentage, double deliveryCost);
}
