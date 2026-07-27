package homework_mock.algos.task_algos;

import java.util.HashSet;
import java.util.Set;

/**
 * Подсчет уникальных символов. Найти количество уникальных символов в строке.
 * <p>
 * "ПриветП" - 6
 */

public class UniqueChars {

    public static int uniqueChars(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        Set<Character> set = new HashSet<>();

        for (char ch : str.toCharArray()) {
            set.add(ch);
        }

        return set.size();
    }

    public static void main(String[] args) {
        System.out.println(uniqueChars("ПриветП"));
        System.out.println(uniqueChars(""));
        System.out.println(uniqueChars(null));
    }
}
