package homework_mock.task_oop.grade_book;

public class Main {

    public static void main(String[] args) {
        GradeBook book = new GradeBook();

        // Добавляем оценки из примера
        book.addGrade("Anna", 5);
        book.addGrade("Anna", 4);
        book.addGrade("Boris", 3);

        System.out.println("=== Оценки ===");
        System.out.println("Anna -> " + book.getGrades("Anna"));
        System.out.println("Boris -> " + book.getGrades("Boris"));

        System.out.println("\n=== Средний балл ===");
        System.out.println("Anna -> " + book.getAverageGrade("Anna"));
        System.out.println("Boris -> " + book.getAverageGrade("Boris"));

        System.out.println("\n=== Дополнительные проверки ===");
        System.out.println("Есть ли Anna в базе? " + book.containsStudent("Anna"));
        System.out.println("Есть ли Sergey в базе? " + book.containsStudent("Sergey"));
        System.out.println("Всего студентов в журнале: " + book.getStudentsCount());
    }
}
