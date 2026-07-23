package homework_mock.algos.task_algos;

import java.util.Arrays;

/**
 * Подсчет слов. Подсчитать количество слов в строке
 *
 * "Привет, как дела?" - 3
 */

public class CountWordsString {

    public static int countWord(String str) {

        if (str == null || str.isEmpty()) {
            return 0;
        }

 return (int) Arrays.stream(str.trim().split("\\s+")).filter(s -> !s.isEmpty()).count();
    }

    public static void main(String[] args) {
        System.out.println(countWord("Привет, как дела?"));
    }
}