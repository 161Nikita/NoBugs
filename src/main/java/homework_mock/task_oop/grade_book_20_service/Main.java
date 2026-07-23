package homework_mock.task_oop.grade_book_20_service;

/*
"В системе есть журнал оценок.

Журнал хранит оценки студентов.
У каждого студента есть:
имя
список его оценок
Нужно реализовать систему, которая может:
добавить оценку студенту
получить все оценки студента
посчитать средний балл студента
проверить, есть ли студент в журнале
показать количество студентов в журнале

Правила:
если студента еще нет в журнале, он создается при первой оценке
у одного студента может быть много оценок
средний балл считается только по его оценкам

Пример:
Добавляем оценки:
Anna → 5
Anna → 4
Boris → 3

Оценки:
Anna → [5, 4]
Boris → [3]

Средний балл:
Anna → 4.5
Boris → 3.0"
 */

public class Main {
    public static void main(String[] args) {
        GradeBook gradeBook = new GradeBook();
        System.out.println("Добавляем оценки:");
        gradeBook.addGradeStudent("Anna", 5);
        System.out.println("Anna → 5");
        gradeBook.addGradeStudent("Anna", 4);
        System.out.println("Anna → 4");
        gradeBook.addGradeStudent("Boris", 3);
        System.out.println("Boris → 3");
        System.out.println("\nОценки");
        System.out.println("Anna → " + gradeBook.getAllGradeStudent("Anna"));
        System.out.println("Boris → " + gradeBook.getAllGradeStudent("Boris"));
        System.out.println("\nСредний балл:");
        System.out.println("Anna → " + gradeBook.averageGradeStudent("Anna"));
        System.out.println("Boris → " + gradeBook.averageGradeStudent("Boris"));
    }
}