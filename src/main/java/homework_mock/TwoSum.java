package homework_mock;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {

    /**
     * Найти два числа в массиве сумма которых равная target и вернуть их индексы
     */

    public static int[] twoSum(int[] array, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            int complement = target - array[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(array[i], i);
        }
        return new int[0];
    }

    public static void main(String[] args) {

        int[] nums = {1, 3, 4, 5, 6, 7, 34, 2213, 4, 1234, 123, -2, 0, 2, -1};

        int target = 7;

        System.out.println("Индексы (ожидается [1, 2]): " + Arrays.toString(twoSum(nums, target)));

    }
}
