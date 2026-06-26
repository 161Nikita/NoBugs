package homework_mock.task_oop.employee;

public abstract class Employee {

    private final String name;
    private final boolean isFemale;

    public Employee(String name, boolean isFemale) {
        this.name = name;
        this.isFemale = isFemale;
    }

    public String getName() {
        return name;
    }

    public boolean isFemale() {
        return isFemale;
    }

    public abstract double calculatorSalary();
}
