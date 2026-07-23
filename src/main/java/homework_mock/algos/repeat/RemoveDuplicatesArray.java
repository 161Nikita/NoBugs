package homework_mock.algos.repeat;

import java.util.Arrays;

/**
 * Удалить дубликаты. Удалить все дубликаты из массива
 * <p>
 * int[] arr = {1, 2, 3, 3, 2, 5} -> {1, 2, 3, 5}
 */

public class RemoveDuplicatesArray {

    public static int[] removeDuplicate(int[] arr) {

        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        return Arrays.stream(arr).distinct().toArray();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(removeDuplicate(new int[]{1, 2, 3, 3, 2, 5}))); // [1, 2, 3, 5]
        System.out.println(Arrays.toString(removeDuplicate(new int[]{}))); // []
        System.out.println(Arrays.toString(removeDuplicate(null))); // []
    }
}