package homework_mock.algos.repeat;

/**
 * Положительные числа. Подсчитать количество положительных чисел
 * <p>
 * [1, -1, 0, 2, 3, 4] -> 4
 * [-1, 0] -> 0
 */

public class CountPositive {

    public static int countPositive(int[] arr) {

        if (arr == null || arr.length == 0) {
            return 0;
        }
        int count = 0;

        for (int num : arr) {
            if (num > 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {

        System.out.println(countPositive(new int[]{1, -1, 0, 2, 3, 4})); // 4
        System.out.println(countPositive(new int[]{-1, 0})); // 0
        System.out.println(countPositive(null)); // 0
    }
}