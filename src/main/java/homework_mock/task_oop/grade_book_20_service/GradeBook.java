package homework_mock.task_oop.grade_book_20_service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradeBook {

    private final Map<String, List<Integer>> journal = new HashMap<>();

    // добавить оценку студенту
    public void addGradeStudent(String name, int grade) {
        List<Integer> grades = journal.getOrDefault(name, new ArrayList<>());
        grades.add(grade);
        journal.put(name, grades);
    }

    // получить все оценки студента
    public List<Integer> getAllGradeStudent(String name) {
        return journal.getOrDefault(name, new ArrayList<>());
    }

    // посчитать средний балл студента
    public double averageGradeStudent(String name) {
        if (!journal.containsKey(name)) {
            return 0.0;
        }
        List<Integer> grades = journal.get(name);

        double count = 0;
        for (int i : grades) {
            count += i;
        }
        int size = grades.size();
        return count / size;
    }

    // проверить, есть ли студент в журнале
    public boolean getStudent(String name) {
        return journal.containsKey(name);
    }
    // показать количество студентов в журнале
    public int countStudent() {
        return journal.size();
    }
}
