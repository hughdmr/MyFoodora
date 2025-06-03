public interface ProfitPolicy {
    public abstract double computeVariable(double targetProfit, double totalIncome, int nbOrders, double serviceFee, double markupPercentage, double deliveryCost);
}
