package homework_mock.task_oop.developer_13service;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String title;
    private List<Developer> developerList = new ArrayList<>();

    public Team(String title) {
        this.title = title;
    }

    // добавить разработчика в команду
    public void addDeveloper(Developer dev) {
        if (dev == null) return;
        this.developerList.add(dev);
    }

    // удалить разработчика из команды
    public void removeDeveloper(Developer dev) {
        this.developerList.remove(dev);
    }

    // найти разработчика по имени
    public void searchDeveloper(String name) {
        if (name == null) {
            return;
        }
        for (Developer d : developerList) {
            if (d.getName().equals(name)) {
                System.out.println(d);
            }
        }
    }

// показать список всех разработчиков
public void getAllDeveloper() {
    if (developerList.isEmpty()) {
        return;
    }
    for (Developer d : developerList) {
        System.out.println(d);
    }
}

// посчитать общий фонд зарплат команды
public void allSalaryTeam() {
    double countSalary = 0;
    for (Developer d : developerList) {
        countSalary += d.getSalary();
    }
    System.out.println((int)countSalary);
}

// вывод в консоль как в тз
public void printTZ() {
    System.out.println("В команде есть разработчики:\n");
    for (Developer d : developerList) {
        System.out.println(d);
    }
    System.out.println("\nОбщий фонд зарплат:\n");
    double countSalary = 0;
    for (Developer d : developerList) {
        countSalary += d.getSalary();
    }
    System.out.println((int) countSalary);
}
}