package homework_mock.task_oop.university_12service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class University {

    private String title;
    private final List<Student> studentList = new ArrayList<>();

    public University(String title) {
        this.title = title;
    }

    // добавить студента в университет
    public void addStudent(Student student) {
        this.studentList.add(student);
    }

    // удалить студента из университета
    public void removeStudent(Student student) {
        this.studentList.remove(student);
    }

    // найти студента по номеру зачетки
    public void searchStudent(int recNum) {
        for (Student s : studentList) {
            if (s.getRecNum() == recNum) {
                System.out.println(s);
            }
        }
    }

    // показать список всех студентов
    public void getAllStudent() {
        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    // посчитать средний балл по университету
    public void averageScoreUniversity() {
        if (studentList.isEmpty()) {
            System.out.println("В университете никто не учится, средний балл 0.00");
            return;
        }
        double count = 0;
        for (Student s : studentList) {
            count += s.getAverageScore();
        }
        double averageUniversity = count / studentList.size();
        System.out.println(String.format(Locale.US, "%.2f", averageUniversity));
    }

    // вывод в консоль как в ТЗ
    public void printAll() {
        System.out.println("В университете есть студенты:\n");
        for (Student s : studentList) {
            System.out.println(s);
        }
        double count = 0;
        for (Student s : studentList) {
            count += s.getAverageScore();
        }
        double averageUniversity = count / studentList.size();
        System.out.println("\nСредний балл по университету:\n");
        System.out.println(String.format(Locale.US, "%.2f", averageUniversity));
    }
}
