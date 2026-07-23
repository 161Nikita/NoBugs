package homework_mock.algos.repeat;

/**
 * Найти наибольшее число в массиве int[] arr = {1, -1, 0, 4, 4}; -> 4
 * Найти наибольшее число в массиве int[] arr = {1}; -> 1
 * При null -> IllegalArgumentException
 * При пустом массиве {} -> IllegalArgumentException
 */
public class MaxNumber {

    public static int maxNum(int[] arr) {

        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Массив не должен быть пустым или null");
        }

        int max = arr[0];

        for (int x : arr) {
            if (max < x) {
                max = x;
            }
        }
        return max;
    }

    public static void main(String[] args) {

        int[] arr1 = {1, -1, 0, 4, 4};
        int[] arr2 = {1};

        System.out.println(maxNum(arr1)); // 4
        System.out.println(maxNum(arr2)); // 1

        try {
            System.out.println(maxNum(null));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + ": Перехватили ошибку, массив не должен быть null");
        }

        try {
            System.out.println(maxNum(new int[]{}));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + ": Перехватили ошибку, массив не должен быть пустым");
        }
    }
}
