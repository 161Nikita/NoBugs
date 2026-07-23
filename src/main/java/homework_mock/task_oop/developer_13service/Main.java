package homework_mock.task_oop.developer_13service;

/*
"В системе есть **команда**.

У команды есть: Team

* название
* список разработчиков

У каждого разработчика есть: Developer

* имя
* должность
* зарплата

Нужно реализовать систему, которая может:

* добавить разработчика в команду
* удалить разработчика из команды
* найти разработчика по имени
* показать список всех разработчиков
* посчитать общий фонд зарплат команды

Правила:

* общий фонд зарплат считается как сумма зарплат всех разработчиков
* если разработчик удален, он больше не участвует в расчете

Пример:

В команде есть разработчики:

* Анна — Backend Developer — 3000
* Борис — QA Engineer — 2500
* Мария — Frontend Developer — 2800

Общий фонд зарплат:

* 8300

"
 */

public class Main {
    public static void main(String[] args) {

        Developer anna = new Developer("Анна", "Backend Developer", 3000);
        Developer boris = new Developer("Борис", "QA Engineer", 2500);
        Developer maria = new Developer("Мария", "Frontend Developer", 2800);

        Team team = new Team("Команда мечты");
        team.addDeveloper(anna);
        team.addDeveloper(boris);
        team.addDeveloper(maria);
        team.getAllDeveloper();
        team.removeDeveloper(boris);
        team.searchDeveloper("Мария");
        team.allSalaryTeam();
        team.printTZ();
    }
}
