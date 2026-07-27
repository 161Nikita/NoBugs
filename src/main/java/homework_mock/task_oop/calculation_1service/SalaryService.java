package homework_mock.task_oop.calculation_1service;

import java.util.List;

public class SalaryService {

    public void allPay(List<Employee> employees) {

        for (Employee employee : employees) {
          int salary = (int) employee.calculateSalary();
            String name = employee.getName();
            String gender = name.endsWith("а") || name.endsWith("я") ? "получила" : "получил";
            System.out.println(employee.getName() + " " + gender + " " + salary);
        }
    }
}
