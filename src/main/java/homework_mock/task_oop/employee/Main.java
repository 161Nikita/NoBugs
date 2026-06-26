package homework_mock.task_oop.employee;

import java.util.ArrayList;
import java.util.List;

/**
 * "Система работает с разными типами сотрудников:
 *
 * сотрудник с фиксированным окладом
 * сотрудник с почасовой оплатой
 * сотрудник с процентом от продаж
 *
 * У каждого сотрудника есть имя.
 *
 * Правила расчета зарплаты:
 * У каждого сотрудника в засимости от типа есть поля:
 * для фиксированная зарплата — месячный оклад
 * для почасовая — количество часов × ставка
 * для процентная — процент × сумма продаж
 *
 * Нужно реализовать систему, которая может выплатить зарплату всем сотрудникам.
 *
 * Требование:
 *
 * Метод, который выплачивает зарплату, должен работать со списком сотрудников, не зная их конкретный тип.
 * Каждый тип сотрудника должен сам рассчитывать свою зарплату.
 *
 * Метод должен вывести:
 * Анна получила 3000
 * Борис получил 2400
 * Мария получила 5000"
 */
public class Main {

   public static void paySalaries(List<Employee> employees) {
       for (Employee employee : employees) {
           int salary = (int) employee.calculatorSalary();
           String action = employee.isFemale()? "получила" : "получил";
           System.out.println(employee.getName() + action + " " + salary);
       }
   }

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();

        employees.add(new SalariedEmployee("Анна ", true, 3000));
        employees.add(new HourlyEmployee("Борис ", false, 160, 15));
        employees.add(new CommissionEmployee("Мария ", true, 50000, 0.10));

        paySalaries(employees);
    }
}
