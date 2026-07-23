package homework_mock.task_oop.calculation_1service;

public abstract class Employee {

    private final String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double calculateSalary();
}
