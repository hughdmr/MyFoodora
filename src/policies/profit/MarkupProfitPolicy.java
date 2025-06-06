package policies.profit;

/**
 *
 * @author gravlax,hugues
 * Implements the ProfitPolicy interface for computing the optimized MarkupPercentage
 * for a target total income
 *
 */
public class MarkupProfitPolicy implements ProfitPolicy {
    public double computeVariable(double targetProfit, double totalIncome, int nbOrders, double serviceFee, double markupPercentage, double deliveryCost) {
        return targetProfit/(nbOrders * (serviceFee - deliveryCost) * totalIncome) * 100;
    }
}
