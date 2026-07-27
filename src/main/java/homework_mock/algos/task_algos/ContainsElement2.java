package homework_mock.algos.task_algos;


/**
 * Содержит элемент. Проверить, есть ли элемент в массиве
 * <p>
 * {1, 2, 3, 4, 5} {5} -> true
 * {1, 2, 3, 4, 5} {6} -> false
 */

public class ContainsElement2 {

    public static boolean isContains(int[] arr, int target) {

        if (arr == null) {
            return false;
        }

        for (int num : arr) {
            if (num == target) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        int[] arr1 = {1, 2, 3, 4, 5};

        System.out.println(isContains(arr1, 3)); // true
        System.out.println(isContains(arr1, 6)); // false
        System.out.println(isContains(new int[]{}, 6)); // false
        System.out.println(isContains(null, 6)); // false

    }
}