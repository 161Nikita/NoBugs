package homework_mock.task_oop.calculation_1service;

public class HourlyEmployee extends Employee{

    private final double hourRate;
    private final int hourWorked;

    public HourlyEmployee(String name, double hourRate, int hourWorked) {
        super(name);
        this.hourRate = hourRate;
        this.hourWorked = hourWorked;
    }

    @Override
    public double calculateSalary() {
        return hourWorked * hourRate;
    }
}
