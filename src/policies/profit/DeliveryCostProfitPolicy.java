package policies.profit;

/**
 *
 * @author gravlax,hugues
 * Implements the ProfitPolicy interface for computing the optimized DeliveryCost
 * for a target total income
 *
 */
public class DeliveryCostProfitPolicy implements ProfitPolicy {
    public double computeVariable(double targetProfit, double totalIncome, int nbOrders, double serviceFee, double markupPercentage, double deliveryCost) {
        return (targetProfit * (markupPercentage / 100.0) + serviceFee*nbOrders - targetProfit)/nbOrders;
    }
}
