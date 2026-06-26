package homework_mock.task_oop.employee;

public class SalariedEmployee extends Employee {

    public double monthlySalary;

    public SalariedEmployee(String name, boolean isFemale, double monthlySalary) {
        super(name, isFemale);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculatorSalary() {
        return monthlySalary;
    }
}
