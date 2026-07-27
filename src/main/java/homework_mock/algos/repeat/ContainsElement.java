package homework_mock.algos.repeat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Проверка наличия элемента в списке. Проверить, содержит ли список заданное число
 *
 * (1, 2, 3) (3) -> true
 * (1, 2, 3) (4) -> false
 * () (4) -> false
 * (1, null, 3) (3) -> true
 * (null) (4) -> IllegalArgumentException
 */

public class ContainsElement {

    public static boolean containsElement(List<Integer> list, int target) {

        if(list == null) {
            throw new IllegalArgumentException("Передача в качестве аргумента null недопустимо");
        }

        return list.contains(target);
    }

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> list2 = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> list3 = new ArrayList<>(List.of());
        List<Integer> list4 = new ArrayList<>(Arrays.asList(1, null, 3));

        System.out.println(containsElement(list1, 3)); // true
        System.out.println(containsElement(list2, 4)); // false
        System.out.println(containsElement(list3, 4)); // false
        System.out.println(containsElement(list4, 3)); // true

        try {
            containsElement(null, 4);
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
    }
}