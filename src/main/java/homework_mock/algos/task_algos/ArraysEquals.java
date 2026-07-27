package homework_mock.algos.task_algos;

import java.util.Arrays;

/**
 * Сравнить массивы. Сравнить два массива по содержанию и порядку.
 *
 * {1, 2, 3, 4}, {1, 2, 3, 4} -> true
 * {}, {} -> true
 * null, null -> true
 * {1, 2, 3, 4}, {1, 3, 2, 4} -> false
 * {1, 2, 3, 4}, {} -> false
 * null, {1, 3, 2, 4} -> false
 */

public class ArraysEquals {

    public static boolean arrayEquals (int[] a, int[] b) {

        boolean result = Arrays.equals(a, b);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(arrayEquals(new int[] {1, 2, 3, 4}, new int[] {1, 2, 3, 4})); // true
        System.out.println(arrayEquals(new int[] {}, new int[] {})); // true
        System.out.println(arrayEquals(null, null)); // true
        System.out.println(arrayEquals(new int[] {1, 2, 3, 4}, new int[] {1, 3, 2, 4})); // false
        System.out.println(arrayEquals(new int[] {1, 2, 3, 4}, new int[] {})); // false
        System.out.println(arrayEquals(null, new int[] {1, 3, 2, 4})); // false
    }
}
