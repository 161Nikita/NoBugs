package homework_mock.algos.repeat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Подсчитать сумму чисел в списке
 * <p>
 * List<Integer> list = new ArrayList(List.of(1, 2, 3, 4, 5)) -> 15
 * List<Integer> list = new ArrayList(List.of(-1, 2, 3, 4, 0)) -> 8
 * List<Integer> list = new ArrayList(List.of(-1, null, 3, 4, 0)) -> IllegalArgumentException
 * {} -> 0
 * {3} -> 3
 * null -> IllegalArgumentException
 */

public class SumOfValues {

    public static int sum(List<Integer> list) {

        if (list == null) {
            throw new IllegalArgumentException("В качестве аргумента не должен быть передан null");
        }
        if (list.isEmpty()) {
            return 0;
        }

        int sum = 0;
        for (Integer x : list) {
            if (x == null) {
                throw new IllegalArgumentException("В списке null не допустим");
            }
            sum += x;
        }
        return sum;
    }

    public static void main(String[] args) {

        List<Integer> list1 = new ArrayList<>(List.of(1, 2, 3, 4, 5)); // 15
        List<Integer> list2 = new ArrayList<>(List.of(-1, 2, 3, 4, 0)); // 8
        List<Integer> list3 = new ArrayList<>(Arrays.asList(-1, null, 3, 4, 0)); // IllegalArgumentException
        List<Integer> list4 = new ArrayList<>(List.of()); // 0
        List<Integer> list5 = new ArrayList<>(List.of(3)); // 3
        List<Integer> list6 = new ArrayList<>();
        list6.add(null); // IllegalArgumentException

        System.out.println(sum(list1));
        System.out.println(sum(list2));
        System.out.println(sum(list4));
        System.out.println(sum(list5));


        try {
            System.out.println(sum(list3));
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }

        try {
            System.out.println(sum(list6));
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }

        try {
            System.out.println(sum(null));
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
    }

}
