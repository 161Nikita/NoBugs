package homework_mock.algos.repeat;

enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

public class EnumDay {

    public static Day getDayFromString(String dayStr) {

        if (dayStr == null) {
            throw new IllegalArgumentException("День недели не может быть null");
        }
        String cleanedDay = dayStr.trim().toUpperCase();
        try {
            return Day.valueOf(cleanedDay);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Такого дня недели не существует! " + dayStr);
        }
    }

    public static void main(String[] args) {

        System.out.println(getDayFromString("MONDAY")); // MONDAY
        System.out.println(getDayFromString(" friday ")); // FRIDAY

        try {
            System.out.println(getDayFromString("MUNDAY"));
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
        try {
            System.out.println(getDayFromString(null));
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
    }
}