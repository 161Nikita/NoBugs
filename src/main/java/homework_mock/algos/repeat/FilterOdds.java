package homework_mock.algos.repeat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Фильтрация нечетных чисел. Оставить в списке только нечетные числа
 * <p>
 * (1, 2, 3, 4, 5) -> [1, 3, 5]
 * (1, 3, 5) -> [1, 3, 5]
 * (1, 1, 1) -> [1, 1, 1]
 * (2, 4, 6) -> []
 * () -> []
 * (null) -> IllegalArgumentException
 * (1, null, 3) -> NullPointerException
 */

public class FilterOdds {

    public static List<Integer> filterOdds(List<Integer> list) {

        if (list == null) {
            throw new IllegalArgumentException("В качестве аргумента null не допустим");
        }

        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        for (Integer x : list) {
            if (x == null) {
                throw new NullPointerException("В качестве элемента в списке null недопустим");
            }
        }

        return list.stream().filter(f -> f % 2 != 0).collect(Collectors.toList());
    }

    public static void main(String[] args) {

        List<Integer> list1 = new ArrayList<>(List.of(1, 2, 3, 4, 5)); // [1, 3, 5]
        List<Integer> list2 = new ArrayList<>(List.of(1, 3, 5)); // [1, 3, 5]
        List<Integer> list3 = new ArrayList<>(List.of(1, 1, 1)); // [1, 1, 1]
        List<Integer> list4 = new ArrayList<>(List.of(2, 4, 6)); // []
        List<Integer> list5 = new ArrayList<>(List.of()); // []
        List<Integer> list6 = new ArrayList<>(Arrays.asList(1, null, 3)); // NullPointerException

        System.out.println(filterOdds(list1));
        System.out.println(filterOdds(list2));
        System.out.println(filterOdds(list3));
        System.out.println(filterOdds(list4));
        System.out.println(filterOdds(list5));

        try {
            filterOdds(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
        try {
            filterOdds(list6);
        } catch (NullPointerException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
    }
}