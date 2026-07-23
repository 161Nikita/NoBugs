package homework_mock.algos.task_algos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Проверка наличия элемента в списке. Проверить, содержит ли список заданное число
 * <p>
 * (1, 2, 3) (3) -> true
 * (1, 2, 3) (4) -> false
 * () (4) -> false
 * (null) (3) -> IllegalArgumentException
 */

public class ContainsElement {


    public static boolean containsElement(List<Integer> list, int target) {

        if (list == null) {
            throw new IllegalArgumentException("В качестве аргумента передавать null недопустимо");
        }

        return list.contains(target);
    }

    public static void main(String[] args) {

        List<Integer> list1 = new ArrayList<>(List.of(1, 2, 3, 4, 5)); // true
        List<Integer> list2 = new ArrayList<>(Arrays.asList(1, null, 3)); // true
        List<Integer> list3 = new ArrayList<>(Arrays.asList(1, 2, 3)); // false
        List<Integer> list4 = new ArrayList<>(Arrays.asList()); // false

        System.out.println(containsElement(list1, 3));
        System.out.println(containsElement(list2, 3));
        System.out.println(containsElement(list3, 4));
        System.out.println(containsElement(list4, 4));

        try {
            containsElement(null, 3);
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
    }
}