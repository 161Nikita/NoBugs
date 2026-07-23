package homework_mock.algos.repeat;

import java.util.Arrays;

/**
 * Разворот массива. Повернуть массив в обратном порядке.
 */

public class ReverseArray {

    public static void reverseArray(int[] arr) {

        if (arr == null || arr.length < 2) {
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

        int[] arr1 = {1, 2, 3, 4};
        reverseArray(arr1);
        System.out.println(Arrays.toString(arr1));
        int[] arr11 = {1, 2};
        reverseArray(arr11);
        System.out.println(Arrays.toString(arr11));

        int[] arr2 = {1};
        reverseArray(arr2);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = {};
        reverseArray(arr3);
        System.out.println(Arrays.toString(arr3));

        int[] arr4 = null;
        reverseArray(arr4);
        System.out.println(Arrays.toString(arr4));

    }
}
