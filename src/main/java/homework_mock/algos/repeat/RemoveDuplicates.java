package homework_mock.algos.repeat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Удалить дубликаты из списка
 * <p>
 * (1, 2, 3) -> [1, 2, 3]
 * (1, 2, 2) -> [1, 2]
 * (2, 2, 2) -> [2]
 * () -> []
 * (1, 2, 2, null) -> NullPointerException
 * (null) -> IllegalArgumentException
 */

public class RemoveDuplicates {


    public static List<Integer> removeDuplicates(List<Integer> list) {

        if (list == null) {
            throw new IllegalArgumentException("В качестве аргумента передача null недопустима");
        }
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        for (Integer x : list) {
            if (x == null) {
                throw new NullPointerException("Элемент null в списке недопустим");
            }
        }
        return new ArrayList<>(new LinkedHashSet<>(list));
    }

    public static void main(String[] args) {

        List<Integer> list1 = new ArrayList<>(List.of(1, 2, 3)); // [1, 2, 3]
        List<Integer> list2 = new ArrayList<>(List.of(1, 2, 2)); // [1, 2]
        List<Integer> list3 = new ArrayList<>(List.of(2, 2, 2)); // [2]
        List<Integer> list4 = new ArrayList<>(Arrays.asList(2, null, 2)); // NullPointerException

        System.out.println(removeDuplicates(list1));
        System.out.println(removeDuplicates(list2));
        System.out.println(removeDuplicates(list3));

        try {
            removeDuplicates(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }

        try {
            removeDuplicates(list4);
        } catch (NullPointerException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
    }
}