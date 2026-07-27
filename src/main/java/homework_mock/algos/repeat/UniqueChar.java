package homework_mock.algos.repeat;

import java.util.HashSet;
import java.util.Set;

/**
 * Подсчет уникальных символов. Найти количество уникальных символов в строке.
 * <p>
 * "ПриветП" - 6
 */

public class UniqueChar {

    public static int uniqueChar(String str) {

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
        System.out.println(uniqueChar("ПриветП")); // 6
        System.out.println(uniqueChar("Привет")); // 6
        System.out.println(uniqueChar("")); // 0
        System.out.println(uniqueChar(null)); // 0
    }
}