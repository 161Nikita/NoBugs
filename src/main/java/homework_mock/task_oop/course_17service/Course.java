package homework_mock.task_oop.course_17service;

import java.util.HashSet;
import java.util.Set;

public class Course {

    private final String titleCourse;
    private final Set<String> regStudentList = new HashSet<>();
    private final Set<String> completedStudentList = new HashSet<>();

    public Course(String titleCourse) {
        this.titleCourse = titleCourse;
    }

    // зарегистрировать студента на курс
    public void addStudentReg(String student) {
        if (student == null) return;
        if (this.regStudentList.add(student)) {
            System.out.println(student);
        }
    }

    // отметить курс как завершенный для студента
    public void addStudentComp(String student) {
        if (student == null) return;
        if (!regStudentList.contains(student)) {
            System.out.println(student + " не был зарегистрирован");
            return;
        }
        if (this.completedStudentList.add(student)) {
            System.out.println(student);
        } else {
            System.out.println("Ошибка: " + student + " уже завершил курс ранее");

        }
    }

    // проверить, зарегистрирован ли студент
    public void checkRegStudent(String student) {
        if (student == null) return;
        if (regStudentList.contains(student)) {
            System.out.println("Да " + student + " зарегистрирован");
        } else {
            System.out.println(student + " не был зарегистрирован");
        }
    }

    // проверить, завершил ли студент курс
    public void checkCompStudent(String student) {
        if (student == null) return;
        if (completedStudentList.contains(student)) {
            System.out.println("Да " + student + " завершил курс");
        } else {
            System.out.println(student + " не завершил курс");
        }
    }

    // посчитать количество зарегистрированных студентов
    public void getAllRegStudent() {
        System.out.println("\nКоличество зарегистрированных:\n" + regStudentList.size());
    }

    // посчитать количество студентов, завершивших курс
    public void getAllCompStudent() {
        System.out.println("\nКоличество завершивших:\n" + completedStudentList.size());
    }
}