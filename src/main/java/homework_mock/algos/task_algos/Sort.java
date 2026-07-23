package homework_mock.algos.task_algos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Сортировка по длине строки. Отсортировать список строк по длине слов. Использовать sort с компаратором
 */

public class Sort {

    public static void sortListByLength(List<String> list) {
        if (list == null) {
            throw new IllegalArgumentException("В качестве аргумента null недопустим");
        }

        if (list.isEmpty()) {
            return;
        }
        list.sort(Comparator.nullsLast(Comparator.comparingInt(String::length)));
    }

    public static void main(String[] args) {

        List<String> words = new ArrayList<>(Arrays.asList("Яблоко", "", "Кот", "Банан", "Дог", "И", "Апельсин"));
        System.out.println(words);
        sortListByLength(words);
        System.out.println(words);

        List<String> words2 = new ArrayList<>(Arrays.asList(null, "", "Кот", "Банан", "Дог", "И", "Апельсин"));
        sortListByLength(words2);
        System.out.println(words2);

        try {
            sortListByLength(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
    }
}
