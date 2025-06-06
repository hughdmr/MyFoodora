package policies.profit;

/**
 *
 * @author gravlax,hugues
 * Implements the ProfitPolicy interface for computing the optimized ServiceFee
 * for a target total income
 *
 */
public class ServiceFeeProfitPolicy implements ProfitPolicy {
    public double computeVariable(double targetProfit, double totalIncome, int nbOrders, double serviceFee, double markupPercentage, double deliveryCost) {
        return (targetProfit + nbOrders*deliveryCost - totalIncome*(markupPercentage/100))/nbOrders;
    }
}
