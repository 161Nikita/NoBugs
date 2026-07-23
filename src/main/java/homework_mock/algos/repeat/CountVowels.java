package homework_mock.algos.repeat;

public class CountVowels {

    /**
     * Подсчитать количество гласных букв в строке (кириллица и латиница)
     * <p>
     * Java -> 2
     * Hello world! -> 3
     * null -> IllegalArgumentException
     * "" -> 0
     * "bbr" -> 0
     */

    public static int countVowelsWord(String s) {
        if (s == null) {
            throw new IllegalArgumentException("В строке не должно быть null");
        }
        int count = 0;

        for (char x : s.toLowerCase().toCharArray()) {
            if ("eyuioaёуеыаоэяию".indexOf(x) >= 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {

        System.out.println(countVowelsWord("Java")); // 2
        System.out.println(countVowelsWord("Hello world!")); // 3
        System.out.println(countVowelsWord("")); // 0
        System.out.println(countVowelsWord("bbr")); // 0

        try {
            System.out.println(countVowelsWord(null));
        } catch (IllegalArgumentException e) {
            System.out.println("Поймали исключение! " + e.getMessage());
        }
    }
}