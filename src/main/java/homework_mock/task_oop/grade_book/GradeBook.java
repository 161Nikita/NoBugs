package homework_mock.task_oop.grade_book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradeBook {
    // Приватное поле по схеме (-)
    private Map<String, List<Integer>> grades;

    // Конструктор инициализирует HashMap
    public GradeBook() {
        this.grades = new HashMap<>();
    }

    // 1. Добавить оценку студенту (+)
    public void addGrade(String student, int grade) {
        // Если студента нет, putIfAbsent создаст для него пустой список
        grades.putIfAbsent(student, new ArrayList<>());
        // Добавляем оценку в список этого студента
        grades.get(student).add(grade);
    }

    // 2. Получить все оценки студента (+)
    public List<Integer> getGrades(String student) {
        // Возвращает список оценок или пустой список, если студента нет
        return grades.getOrDefault(student, new ArrayList<>());
    }

    // 3. Посчитать средний балл студента (+)
    public double getAverageGrade(String student) {
        List<Integer> studentGrades = grades.get(student);

        // Если студента нет или у него нет оценок, средний балл 0.0
        if (studentGrades == null || studentGrades.isEmpty()) {
            return 0.0;
        }

        double sum = 0;
        for (int grade : studentGrades) {
            sum += grade;
        }
        return sum / studentGrades.size();
    }

    // 4. Проверить, есть ли студент в журнале (+)
    public boolean containsStudent(String student) {
        return grades.containsKey(student);
    }

    // 5. Показать количество студентов в журнале (+)
    public int getStudentsCount() {
        return grades.size();
    }
}
