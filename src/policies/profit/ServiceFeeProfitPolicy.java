package policies.profit;

public class ServiceFeeProfitPolicy implements ProfitPolicy {
    public double computeVariable(double targetProfit, double totalIncome, int nbOrders, double serviceFee, double markupPercentage, double deliveryCost) {
        return (targetProfit + nbOrders*deliveryCost - totalIncome*(markupPercentage/100))/nbOrders;
    }
}
