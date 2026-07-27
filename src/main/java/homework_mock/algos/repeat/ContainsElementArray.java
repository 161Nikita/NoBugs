package homework_mock.algos.repeat;

/**
 * Содержит элемент. Проверить, есть ли элемент в массиве
 * <p>
 * {1, 2, 3, 4, 5} {5} -> true
 * {1, 2, 3, 4, 5} {6} -> false
 * {} - false
 * null -> false
 */

public class ContainsElementArray {

    public static boolean isContainsElement(int[] arr, int target) {

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
        System.out.println(isContainsElement(new int[]{1, 2, 3, 4, 5}, 5)); // true
        System.out.println(isContainsElement(new int[]{1, 2, 3, 4, 5}, 6)); // false
        System.out.println(isContainsElement(new int[]{}, 6)); // false
        System.out.println(isContainsElement(null, 6)); // false
    }
}