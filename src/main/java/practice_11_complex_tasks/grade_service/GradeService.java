package practice_11_complex_tasks.grade_service;

import java.util.ArrayList;
import java.util.List;

public class GradeService<T extends Number> {

    private final List<StudentGrade<T>> grades = new ArrayList<>();

    private synchronized void addGrade(StudentGrade<T> grade) {
        if (grade == null) {
            throw new InvalidGradeException("Оценка не может быть null");
        }
        if (grade.getGrade().doubleValue() < 0) {
            throw new InvalidGradeException("Оценки не может быть отрицательна");
        }
        grades.add(grade);
    }

    private synchronized double getAverageGradeBySubject(String subjectName) {
        return grades.stream().filter(s -> s.getSubject().equalsIgnoreCase(subjectName))
                .mapToDouble(g -> g.getGrade().doubleValue()).average()
                .orElseThrow(() -> new InvalidGradeException("Нет оценок по предмету: " + subjectName));
    }

    public List<StudentGrade<T>> getAllGrades() {
        return List.copyOf(grades);
    }
}