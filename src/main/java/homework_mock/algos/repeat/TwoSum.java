package homework_mock.algos.repeat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Сумма двух чисел. Найти два числа в массиве, сумма которых равна target, и вернуть их индексы
 */

public class TwoSum {

    public static int[] twoSum(int[] arr, int target) {

        if (arr == null || arr.length < 2) {
            System.out.println("Массив не должен быть null или быть меньше 2 элементов");
            return new int[0];
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {

            int complement = target - arr[i];
            if (map.containsKey(complement)) {
                return new int[] {map.get(complement), i};
            }
            map.put(arr[i], i);
        }
        return new int[0];
    }

    public static void main(String[] args) {

        int[] a = {1, 2, 3, 4};
        int[] b = {1, 2};
        int[] c = {1};

        System.out.println(Arrays.toString(twoSum(a, 4))); // [0, 2]
        System.out.println(Arrays.toString(twoSum(b, 3))); // [0, 1]
        System.out.println(Arrays.toString(twoSum(b, 4))); // []
        System.out.println(Arrays.toString(twoSum(c, 1))); // message + []
    }
}
