package homework_mock.algos.repeat;

/**
 * Минимум в массиве. Найти минимальное число в массиве.
 * <p>
 * [1, 2, 3, 4] -> 1
 * [-11, 0, 3, 4] -> -11
 * [] -> IllegalArgumentException
 * null -> IllegalArgumentException
 */

public class MinNumArray {

    public static int minNumArray(int[] arr) {

        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Передача null и пустого массива не допустима");
        }

        int min = arr[0];

        for (int num : arr) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(minNumArray(new int[]{1, 2, 3, 4})); // 1
        System.out.println(minNumArray(new int[]{-11, 0, 3, 4})); // -11
        System.out.println(minNumArray(new int[]{0})); // 0
        try {
            System.out.println(minNumArray(new int[]{})); // IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
        try {
            System.out.println(minNumArray(null)); // IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
    }
}