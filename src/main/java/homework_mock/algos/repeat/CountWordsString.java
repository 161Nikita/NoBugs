package homework_mock.algos.repeat;

import java.util.Arrays;

/**
 * Подсчет слов. Подсчитать количество слов в строке
 * <p>
 * "Привет, как дела?" - 3
 */

public class CountWordsString {

    public static int countWords(String str) {

        if (str == null || str.isEmpty()) {
            return 0;
        }

        return (int) Arrays.stream(str.trim().split("\\s+")).filter(s -> !s.isEmpty()).count();
    }

    public static void main(String[] args) {
        System.out.println(countWords("Привет, как дела?"));
        System.out.println(countWords("                Привет, "));
        System.out.println(countWords(""));
        System.out.println(countWords(null));
    }
}