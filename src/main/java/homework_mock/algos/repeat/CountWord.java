package homework_mock.algos.repeat;

public class CountWord {

    /**
     * Подсчитать количество слов в строке
     * <p>
     * "Я люблю Java" -> 3
     * " Я люблю Java" -> 3
     * "ЛЮБЛЮ" -> 1
     * " Я люблю   java и спать" -> 5
     * "" -> 0
     * "  " -> 0
     * null -> IllegalArgumentException
     *
     */

    public static int countWord(String str) {

        if (str == null) {
            throw new IllegalArgumentException("Строка не может быть null");
        }

        String trimmed = str.trim();
        if (trimmed.isEmpty()) {
            return 0;
        }
        return trimmed.split("\\s+").length;
    }

    public static void main(String[] args) {
        System.out.println(countWord("Я люблю Java")); // 3
        System.out.println(countWord(" Я люблю Java")); // 3
        System.out.println(countWord("ЛЮБЛЮ")); // 1
        System.out.println(countWord(" Я люблю   java и спать")); // 5
        System.out.println(countWord("")); // 0
        System.out.println(countWord("  ")); // 0

        try {
            System.out.println(countWord(null));
        } catch (IllegalArgumentException e) {
            System.out.println("Отловили исключение! " + e.getMessage());
        }
    }

}