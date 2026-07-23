package homework_mock.algos.task_algos;

import java.util.Arrays;

/**
 * Удалить дубликаты из массива
 * <p>
 * int[] arr = {1, 2, 3, 3, 4, 5, 5}; - {1, 2, 3, 4, 5}
 */

public class RemoveDuplicatesArray {

    public static int[] removeDuplicates(int[] arr) {

        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        return Arrays.stream(arr).distinct().toArray();
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(removeDuplicates(new int[]{1, 2, 3}))); // [1, 2, 3]
        System.out.println(Arrays.toString(removeDuplicates(new int[]{1, 2, 3, 3, 2}))); // [1, 2, 3]
        System.out.println(Arrays.toString(removeDuplicates(new int[]{1, 2, 3, 3, 4, 5, 5}))); // [1, 2, 3, 4, 5]
    }
}