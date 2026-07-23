package homework_mock.task_oop.university_12service;

/*
"В системе есть **университет**.

У университета есть: University

* название
* список студентов

У каждого студента есть: Student

* имя
* номер зачетки
* средний балл

Нужно реализовать систему, которая может:

* добавить студента в университет
* удалить студента из университета
* найти студента по номеру зачетки
* показать список всех студентов
* посчитать средний балл по университету

Правила:

* средний балл по университету считается как среднее значение баллов всех студентов
* если студент удален, он больше не участвует в расчете

Пример:

В университете есть студенты:

* Анна — 101 — 4.8
* Борис — 102 — 4.2
* Мария — 103 — 5.0

Средний балл по университету:

* 4.67

"
 */


public class Main {
    public static void main(String[] args) {
        Student anna = new Student("Анна", 101, 4.8);
        Student boris = new Student("Борис", 102, 4.2);
        Student maria = new Student("Мария", 103, 5.0);


        University university = new University("БГУЭП");
        university.addStudent(anna);
        university.addStudent(boris);
        university.addStudent(maria);
        university.printAll();




    }
}
