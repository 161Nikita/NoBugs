package homework_mock.task_oop.calculation_1service;

public class PercentEmployee extends Employee{

    private final double percent;
    private final double salesAmount;

    public PercentEmployee(String name, double percent, double salesAmount) {
        super(name);
        this.percent = percent;
        this.salesAmount = salesAmount;
    }
    @Override
    public double calculateSalary() {
        return percent * salesAmount;
    }
}
