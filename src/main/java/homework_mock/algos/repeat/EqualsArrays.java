package homework_mock.algos.repeat;

import java.util.Arrays;

/**
 * * Сравнить массивы. Сравнить два массива по содержанию и порядку.
 * *
 * * {1, 2, 3, 4}, {1, 2, 3, 4} -> true
 * * {}, {} -> true
 * * null, null -> true
 * * {1, 2, 3, 4}, {1, 3, 2, 4} -> false
 * * {1, 2, 3, 4}, {} -> false
 * * null, {1, 3, 2, 4} -> false
 *
 */

public class EqualsArrays {

    public static boolean isEqualsArrays(int[] a, int[] b) {

        boolean result = Arrays.equals(a, b);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(isEqualsArrays(new int[]{1, 2, 3, 4}, new int[]{1, 2, 3, 4})); // true
        System.out.println(isEqualsArrays(new int[]{}, new int[]{})); // true
        System.out.println(isEqualsArrays(null, null)); // true
        System.out.println(isEqualsArrays(new int[]{1, 2, 3, 4}, new int[]{1, 3, 2, 4})); // false
        System.out.println(isEqualsArrays(new int[]{1, 2, 3, 4}, new int[]{})); // false
        System.out.println(isEqualsArrays(null, new int[]{})); // false
    }
}