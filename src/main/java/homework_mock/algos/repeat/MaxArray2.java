package homework_mock.algos.repeat;

/**
 * Максимум в массиве. Найти максимальный элемент.
 * <p>
 * [1, 0, -1, 5, 1] - 5
 */

public class MaxArray2 {

    public static int maxNumArray(int[] arr) {

        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Массив не может быть null или пуст");
        }

        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {

        int[] arr1 = {1, 0, -1, 5, 1};

        System.out.println(maxNumArray(arr1)); // 5
    }
}