package homework_mock.task_oop.developer_13service;

import java.util.Objects;

public class Developer {
    private String name;
    private String role;
    private double salary;

    public Developer(String name, String role, double salary) {
        this.name = name;
        this.role = role;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return name + " — " + role + " — " + (int) salary;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return Objects.equals(name, developer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
