package homework_mock.algos.repeat;

public class SecondMax {

    /**
     * Поиск второго максимального элемента в массиве
     * <p>
     * {-3, 0, 1, 3, 3} -> 1
     * null || < 2 элементов -> IllegalArgumentException
     * {3, 3, 3} -> IllegalArgumentException
     */

    public static int secondMax(int[] arr) {

        if (arr == null || arr.length < 2) {
            throw new IllegalArgumentException("Массив не должен быть null и состоять меньше двух элементов");
        }

        long max = Long.MIN_VALUE;
        long second = Long.MIN_VALUE;

        for (int n : arr) {
            if (n > max) {
                second = max;
                max = n;
            } else if (n > second && n != max) {
                second = n;
            }
        } if (second == Long.MIN_VALUE) {
            throw new IllegalArgumentException("Второго максимального элемента нет");
        }
        return (int) second;
    }

    public static void main(String[] args) {
        int[] arr1 = {-3, 0, 1, 3, 3};

        System.out.println(secondMax(arr1)); // 1

        try {
            System.out.println(secondMax(new int[]{1}));
        } catch (IllegalArgumentException e) {
            System.out.println("Поймали исключение! " + e.getMessage());
        }
        try {
            System.out.println(secondMax(null));
        } catch (IllegalArgumentException e) {
            System.out.println("Поймали исключение! " + e.getMessage());
        }
        try {
            System.out.println(secondMax(new int[]{3, 3, 3}));
        } catch (IllegalArgumentException e) {
            System.out.println("Поймали исключение! " + e.getMessage());
        }
    }

}

