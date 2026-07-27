package homework_mock.algos.repeat;

/**
 * Подсчет гласных. Посчитать количество гласных в строке
 * <p>
 * "Привет" - 2
 * "Hi" - 1
 */

public class CountVowels2 {

    public static int countVowels(String s) {

        if (s == null) {
            return 0;
        }

        int count = 0;

        for (char ch : s.toLowerCase().toCharArray()) {
            if ("eyuioaёуеыаоэяию".indexOf(ch) >= 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countVowels("Hi")); // 1
        System.out.println(countVowels("Privet")); // 2
        System.out.println(countVowels("Привет")); // 2
        System.out.println(countVowels("")); // 0
        System.out.println(countVowels(null)); // 0
    }
}