package homework_mock.algos.task_algos;

/**
 * Максимум в массиве. Найти максимальный элемент
 * <p>
 * [1, 2, 3, 4] - 4
 */

public class MaxArray2 {

    public static int maxNumArray(int[] arr) {

        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Массив не должен быть null или пустым");
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

        int[] arr1 = {1, 2, 2, 5, -1, 0};

        System.out.println(maxNumArray(arr1));
    }
}