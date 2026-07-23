package homework_mock.task_oop.calculation_1service;

public class FixedEmployee extends Employee {

    private final int monthlySalary ;

    public FixedEmployee(String name, int monthlySalary ) {
        super(name);
        this.monthlySalary  = monthlySalary ;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary ;
    }
}
