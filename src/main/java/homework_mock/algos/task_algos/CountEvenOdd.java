package homework_mock.algos.task_algos;

import java.util.Arrays;

/**
 * Четные нечетные. Подсчитать четные и нечетные числа
 * <p>
 * [1, 2, 3, 4, 5] - 2, 3
 * [1, 3, 5] -   0, 3
 * [2, 4] -  2,  0
 * [] -  0, 0
 * null -  0, 0
 */

public class CountEvenOdd {

    public static int[] countEvenOdd(int[] arr) {

        if (arr == null || arr.length == 0) return new int[]{0, 0};

        int even = 0;
        int odd = 0;

        for (int num : arr) {
            if (num % 2 == 0) even++;
            else {
                odd++;
            }
        }
        return new int[]{even, odd};
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(countEvenOdd(new int[]{1, 2, 3, 4, 5}))); // 2, 3
        System.out.println(Arrays.toString(countEvenOdd(new int[]{1, 3, 5}))); // 0, 3
        System.out.println(Arrays.toString(countEvenOdd(new int[]{2, 4}))); // 2, 0
        System.out.println(Arrays.toString(countEvenOdd(new int[]{}))); // 0, 0
        System.out.println(Arrays.toString(countEvenOdd(null))); // 0, 0
    }
}