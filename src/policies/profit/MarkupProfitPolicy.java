package policies.profit;

public class MarkupProfitPolicy implements ProfitPolicy {
    public double computeVariable(double targetProfit, double totalIncome, int nbOrders, double serviceFee, double markupPercentage, double deliveryCost) {
        return targetProfit/(nbOrders * (serviceFee - deliveryCost) * totalIncome) * 100;
    }
}
