package homework_mock.task_oop.employee;

public class HourlyEmployee extends Employee {

    private int hoursWorked;
    private double hourlyRate;

    public HourlyEmployee(String name, boolean isFemale, int hoursWorked, double hourlyRate) {
        super(name, isFemale);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculatorSalary() {
        return hourlyRate * hoursWorked;
    }
}
