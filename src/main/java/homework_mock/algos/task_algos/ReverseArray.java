package homework_mock.algos.task_algos;

import java.util.Arrays;

/**
 * Разворот массива. Перевернуть массив в обратном порядке.
 * <p>
 * [1, 2, 3, 4] -> [4, 3, 2, 1]
 */

public class ReverseArray {

    public static void reversArray(int[] arr) {

        if (arr == null || arr.length <= 1) {
            return;
        }

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {

            int temp = arr[left];
            arr[left++] = arr[right];
            arr[right--] = temp;
        }
    }

    public static void main(String[] args) {

        int[] arr = {1 ,2 ,3 ,4};
      reversArray(arr);
        System.out.println(Arrays.toString(arr));

        int[] arr2 = {1};
        reversArray(arr2);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = {};
        reversArray(arr3);
        System.out.println(Arrays.toString(arr3));

        int[] arr4 = null;
        reversArray(arr4);
        System.out.println(Arrays.toString(arr4));
    }
}
