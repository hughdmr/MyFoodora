package policies.profit;

public interface ProfitPolicy {
    double computeVariable(double targetProfit, double totalIncome, int nbOrders, double serviceFee, double markupPercentage, double deliveryCost);
}
