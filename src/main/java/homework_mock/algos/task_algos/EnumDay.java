package homework_mock.algos.task_algos;

/**
 * Просто перечисление дней недели. Создать enum Day с 7 днями недели.
 */
enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

public class EnumDay {

    public static Day getDayFromString(String dayStr) {
        if (dayStr == null) {
            throw new IllegalArgumentException("День недели не может быть null");
        }
        String cleanedInput = dayStr.trim().toUpperCase();

        try {
            return Day.valueOf(cleanedInput);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Такого дня недели не существует " + dayStr);
        }
    }

    public static void main(String[] args) {

        System.out.println(getDayFromString("MONDAY"));
        System.out.println(getDayFromString(" friday "));
        try {
            System.out.println(getDayFromString("MUNDAY"));
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили ошибку: " + e.getMessage());
        }
        try {
            getDayFromString(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили null: " + e.getMessage());
        }
    }
}