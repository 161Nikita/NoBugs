package homework_mock.algos.task_algos;

import java.util.HashSet;
import java.util.Set;

/**
 * Проверка уникальности элементов. Проверить, все ли элементы в массиве уникальны
 */

public class AllUnique {

    public static boolean allUnique(int[] arr) {


        if (arr == null) {
            throw new IllegalArgumentException("Массив не должен быть null");
        }

        if (arr.length == 0) {
            System.out.println("Массив пуст");
            return true;
        }
        Set<Integer> set = new HashSet<>();

        for (int num : arr) {
            if (!set.add(num))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {

        System.out.println(allUnique(new int[]{1, 2, 3, 4})); // true
        System.out.println(allUnique(new int[]{1, 2, 3, 3})); // false
        System.out.println(allUnique(new int[]{})); // Массив пуст

        try {
            allUnique(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили null " + e.getMessage());
        }
    }


}
