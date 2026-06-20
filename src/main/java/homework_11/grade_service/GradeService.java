package homework_11.grade_service;

import java.util.ArrayList;
import java.util.List;

public class GradeService<T extends Number> {

    private final List<StudentGrade<T>> grades = new ArrayList<>();

    public synchronized void addGrade(StudentGrade<T> grade) {
        if (grade == null || grade.getGrade().doubleValue() < 0) {
            throw new InvalidGradeException("Ошибка: Оценка не может быть отрицательной или быть null");
        }
        grades.add(grade);
    }

    public synchronized double avgGradeSubjectName(String subjectName) {
        return grades.stream().filter(s -> s.getSubjectName().equalsIgnoreCase(subjectName))
                .mapToDouble(g -> g.getGrade().doubleValue())
                .average().orElseThrow(() -> new InvalidGradeException("Оценка не может быть отрицательной или такого предмета не нашлось"));
    }

    public List<StudentGrade<T>> getAll() {
        return List.copyOf(grades);
    }
}
