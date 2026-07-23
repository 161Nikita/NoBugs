package homework_mock.algos.task_algos;

public class CountWords {

    /**
     * Подсчет слов в строке
     * <p>
     * "Я люблю Java" -> 3
     * " Я люблю Java " -> 3
     * " Z " -> 1
     * "  " -> 0
     * "" -> 0
     * null -> IllegalArgumentException
     *
     */

    public static int countWord(String str) {

        if (str == null) {
            throw new IllegalArgumentException("Строка не должна быть null");
        }

        String trimmed = str.trim();
        if (trimmed.isEmpty()) {
            return 0;
        }
        return trimmed.split("\\s+").length;
    }

    public static void main(String[] args) {
        String str = "Я люблю Java";
        String str2 = " Я люблю   Java ";
        String str3 = " Я ";

        System.out.println(countWord(str)); // 3
        System.out.println(countWord(str2)); // 3
        System.out.println(countWord(str3)); // 1
        System.out.println(countWord("  ")); // 0
        System.out.println(countWord("")); // 0


        try {
            System.out.println(countWord(null)); //IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println("Поймали исключение! " + e.getMessage());
        }
    }
}
