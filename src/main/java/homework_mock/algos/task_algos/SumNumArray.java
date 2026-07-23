package homework_mock.algos.task_algos;

public class SumNumArray {

    /**
     * Сумма чисел в массиве
     * <p>
     * int [] arr = {5, 4, 3, 2, 1}; -> 15
     */

    public static int sumNum(int[] arr) {

        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int x : arr) {
            sum = sum + x;
        }
        return sum;
    }

    public static void main(String[] args) {

        int[] arr1 = {};
        int[] arr2 = null;
        int[] arr3 = {-1, 2, -4};
        int[] arr4 = {4, 3, 2, 1};

        System.out.println(sumNum(arr1));
        System.out.println(sumNum(arr2));
        System.out.println(sumNum(arr3));
        System.out.println(sumNum(arr4));
    }
}
