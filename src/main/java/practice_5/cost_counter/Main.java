package practice_5.cost_counter;

public class Main {
    public static void main(String[] args) {
        CostCounter costCounter = new CostCounter();

        costCounter.addCosts(1, 123.4);
        costCounter.addCosts(2, 1563.3);
        costCounter.addCosts(3, 4122.3);
        costCounter.addCosts(4, 26713.1);
        costCounter.addCosts(5, 85731.5);

        System.out.println(costCounter.getCosts(3));
        System.out.println(costCounter.minCostsPerMonth());
    }
}
