package homework_mock.algos.repeat;

import java.util.Arrays;

/**
 * Объединить отсортированные массивы в один отсортированный.
 */

public class MergeSortedArrays {

    public static int[] mergeSortArray(int[] a, int[] b) {

        if (a == null || b == null) {
            throw new IllegalArgumentException("Передача null в качестве аргумента недопустима");
        }

        int[] result = new int[a.length + b.length];

        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);

        Arrays.sort(result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(mergeSortArray(new int[]{1, 2, 3}, new int[]{4, 5, 6}))); // [1, 2, 3, 4, 5, 6]
        System.out.println(Arrays.toString(mergeSortArray(new int[]{1, 2, 3}, new int[]{5, 6}))); //[1, 2, 3, 5, 6]
        System.out.println(Arrays.toString(mergeSortArray(new int[]{}, new int[]{5, 6}))); //[5, 6]

        try {
            System.out.println(Arrays.toString(mergeSortArray(null, new int[]{5, 6}))); //IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }

    }
}