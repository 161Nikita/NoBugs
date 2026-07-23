package homework_mock.algos.repeat;

import java.util.HashSet;
import java.util.Set;

/**
 * Проверка уникальности. Проверить, все ли элементы в массиве уникальны?
 * <p>
 * {1, 2, 3, 4} -> true
 * {1, 2, 3, 3} - false
 * {} -> Массив пустой
 * null -> IllegalArgumentException
 *
 */
public class AllUnique {

    public static boolean isAllUnique(int[] arr) {

        if (arr == null) {
            throw new IllegalArgumentException("В качестве аргумента null не должен передаваться");
        }

        if (arr.length == 0) {
            System.out.println("Массив пустой!");
            return true;
        }

        Set<Integer> set = new HashSet<>();

        for (int x : arr) {
            if (!(set.add(x))) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {

        int[] arr1 = {1, 2, 3, 4}; // true
        int[] arr2 = {1, 2, 3, 3}; // false
        int[] arr3 = {}; // Пустой массив true

        System.out.println(isAllUnique(arr1));
        System.out.println(isAllUnique(arr2));
        System.out.println(isAllUnique(arr3));

        try {
            isAllUnique(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
    }
}
