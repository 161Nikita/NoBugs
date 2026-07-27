package homework_mock.algos.task_algos;


import java.util.Arrays;

/**
 * Объединить отсортированные массивы. Объединить два отсортированных массива в один отсортированный.
 */

public class MergeSortedArrays {

    public static int[] mergeSortedArray(int[] a, int[] b) {

        if (a == null || b == null) {
            throw new IllegalArgumentException("В качестве аргумента null не должен передаваться");
        }

        int[] result = new int[a.length + b.length];

        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);

        Arrays.sort(result);

        return result;
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(mergeSortedArray(new int[]{1, 2, 3, 4}, new int[]{5, 6})));
        System.out.println(Arrays.toString(mergeSortedArray(new int[]{1, 2, 3, 4}, new int[]{})));

        try {
            System.out.println(Arrays.toString(mergeSortedArray(new int[]{1, 2, 3, 4}, null)));
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
    }
}