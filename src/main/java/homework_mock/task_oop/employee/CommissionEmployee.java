package homework_mock.task_oop.employee;

public class CommissionEmployee extends Employee {

    private double salesAmount;
    private double commissionRate;

    public CommissionEmployee(String name, boolean isFemale, double salesAmount, double commissionRate) {
        super(name, isFemale);
        this.salesAmount = salesAmount;
        this.commissionRate = commissionRate;
    }

    @Override
    public double calculatorSalary() {
        return salesAmount * commissionRate;
    }
}
