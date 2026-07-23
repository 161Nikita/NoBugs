package homework_mock.algos.task_algos;

public class CountVowels {
    /**
     * Подсчитать количество гласных букв в строке
     * <p>
     * Java -> 2
     * Hello word! -> 3
     * null -> IllegalArgumentException
     * "" -> 0
     * bbr -> 0
     */

    public static int countVowelsWord(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Строка не может быть null");
        }
        int count = 0;

        for (char c : str.toLowerCase().toCharArray()) {
            if ("aeiouаеёиоуыэюя".indexOf(c) >= 0)
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countVowelsWord("Java"));
        System.out.println(countVowelsWord("Hello word!"));
        System.out.println(countVowelsWord(""));
        System.out.println(countVowelsWord("bbr"));

        try {
            System.out.println(countVowelsWord(null));
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
    }
}

