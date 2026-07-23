package homework_mock.algos.task_algos;

public class SecondMax {

    /**
     * Поиск второго максимального элемента
     * <p>
     * {-3,0,3,4} -> 3
     * <p>
     * null -> IllegalArgumentException
     * {} -> IllegalArgumentException
     * {3,3,3} -> IllegalArgumentException (второго максимума нет)
     */

    public static int secondMax(int[] arr) {

        if (arr == null || arr.length < 2) {
            throw new IllegalArgumentException("Массив не должен быть null и содержать меньше 2 элементов");
        }

        int max = Math.max(arr[0], arr[1]);
        int second = Math.min(arr[0], arr[1]);

        for (int i = 2; i < arr.length; i++) {
            int n = arr[i];

            if (n > max) {
                second = max;
                max = n;
            } else if (n > second && n != max) {
                second = n;
            }
        }
        if (max == second) {
            throw new IllegalArgumentException("Второго элемента нет");
        }
        return second;
    }
}
