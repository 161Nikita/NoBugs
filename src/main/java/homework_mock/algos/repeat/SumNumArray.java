package homework_mock.algos.repeat;

/**
 * Дан массив чисел, нужно вывести сумму чисел массива int[] arr = {1, 2, -2, 0, 5}; {}, null
 */

public class SumNumArray {

    public static int sumNum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = 0;

        for (int x : arr) {
            sum += x;
        }
        return sum;
    }

    public static void main(String[] args) {

        int[] arr1 = {1, 2, -2, 0, 5}; // 6
        int[] arr2 = {}; // 0
        int[] arr3 = null; // 0

        System.out.println(sumNum(arr1));
        System.out.println(sumNum(arr2));
        System.out.println(sumNum(arr3));
    }
}
