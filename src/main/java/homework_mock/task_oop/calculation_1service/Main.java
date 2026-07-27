package homework_mock.task_oop.calculation_1service;

import java.util.ArrayList;
import java.util.List;

/**
 *"Система работает с разными типами сотрудников:
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
    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();

        employees.add(new FixedEmployee("Анна", 3000));
        employees.add(new HourlyEmployee("Борис", 15.0, 160));
        employees.add(new PercentEmployee("Мария", 0.10, 50000));

        SalaryService salaryService = new SalaryService();

        salaryService.allPay(employees);
    }
}
