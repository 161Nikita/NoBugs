package homework_mock.algos.task_algos;

public class MaxNumber {

    /**
     * Дано: Поиск максимального числа в массиве
     * Пример массива -> int[] arr = {1, 3, -2, 0, 4}; -> 4
     * Поиск максимального числа в массиве.
     */

    public static int maxNum(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Массив не должен быть null или пустым");
        }

        int max = arr[0];

        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, -2, 0, 4};
        int[] arr2 = {5, 4, -2, 0, 4};
        int[] arr3 = {5};


        System.out.println("Максимум arr1: " + maxNum(arr1)); // Выведет 4
        System.out.println("Максимум arr2: " + maxNum(arr2)); // Выведет 5
        System.out.println("Максимум arr3: " + maxNum(arr3)); // Выведет 5

        try {
            maxNum(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Тест null успешно перехвачен: " + e.getMessage());
        }

        try {
            maxNum(new int[]{}); // Пустой массив
        } catch (IllegalArgumentException e) {
            System.out.println("Тест пустого массива успешно перехвачен: " + e.getMessage());
        }
    }
}