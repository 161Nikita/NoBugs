package homework_mock.task_oop.university_12service;

import java.util.Objects;

public class Student {

    private String name;
    private int recNum;
    private double averageScore;

    public Student(String name, int recNum, double averageScore) {
        this.name = name;
        this.recNum = recNum;
        this.averageScore = averageScore;
    }

    public String getName() {
        return name;
    }

    public int getRecNum() {
        return recNum;
    }

    public double getAverageScore() {
        return averageScore;
    }

    @Override
    public String toString() {
        return name + " — " + recNum + " — " + averageScore;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return recNum == student.recNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(recNum);
    }
}
