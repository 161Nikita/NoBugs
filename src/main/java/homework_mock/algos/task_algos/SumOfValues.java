package homework_mock.algos.task_algos;

import java.util.ArrayList;
import java.util.List;

/**
 * Сумма значений в списке. Подсчитать сумму всех чисел в list<Integer>
 * <p>
 * List<Integet> list = {1, 2, 3, 4, 5}; -> 15
 * List<Integet> list = {-1, 2, 3, 4, 0}; -> 8
 * <p>
 * {}; -> 0
 * {1}; -> 1
 * null -> IllegalArgumentException
 */

public class SumOfValues {

    public static int sum(List<Integer> list) {

        if (list == null) {
            throw new IllegalArgumentException("В качестве аргумента не должен передаваться null");
        }

        if (list.isEmpty()) {
            return 0;
        }

        int sum = 0;

        for (Integer x : list) {
            if (x != null) {
                sum += x;
            }
        }
        return sum;
    }

    public static void main(String[] args) {

        List<Integer> list1 = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        List<Integer> list2 = new ArrayList<>(List.of());
        List<Integer> list3 = new ArrayList<>(List.of(-1, 2, 3, 4, 0));

        System.out.println(sum(list1));
        System.out.println(sum(list2));
        System.out.println(sum(list3));

        try {
            System.out.println(sum(null));
        } catch (IllegalArgumentException e) {
            System.out.println("Поймали исключение! " + e.getMessage());
        }
    }

}