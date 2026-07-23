package homework_mock.algos.repeat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Сортировка по длине строки. Отсортировать список слов по длине слов.
 * <p>
 * "Привет" "кот" "дог" -> "кот", "дог", "Привет"
 * "кот", null, "кактус" -> "кот", "кактус", null
 * null -> IllegalArgumentException
 */

public class Sort {

    public static void sortWords(List<String> list) {

        if (list == null) {
            throw new IllegalArgumentException("В качестве аргумента null не должен передаваться");
        }
        if (list.isEmpty()) {
            return;
        }
        list.sort(Comparator.nullsLast(Comparator.comparingInt(String::length)));
    }

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>(Arrays.asList("Привет", "кот", "дог"));
        sortWords(list1);
        System.out.println(list1);

        List<String> list2 = new ArrayList<>(Arrays.asList("кот", null, "кактус"));
        sortWords(list2);
        System.out.println(list2);

        try {
            sortWords(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
    }
}
