package policies.profit;

public class DeliveryCostProfitPolicy implements ProfitPolicy {
    public double computeVariable(double targetProfit, double totalIncome, int nbOrders, double serviceFee, double markupPercentage, double deliveryCost) {
        return (targetProfit * (markupPercentage / 100.0) + serviceFee*nbOrders - targetProfit)/nbOrders;
    }
}
