package homework_mock;

/*
Поиск второго по величине элемента в массиве за О(N) времени и O(1) памяти.
Важно: правильно обработать дубликаты максимального числа.
*/
public class SecondMax {

    public static int findSecondMax(int[] nums) {
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("Массив слишком мал");
        }

        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num > max) {
                secondMax = max;
                max = num;
            } else if (num > secondMax && num != max) {
                secondMax = num;
            }
        }

        if (secondMax == Integer.MIN_VALUE) {
            throw new RuntimeException("Второго максимума нет (все элементы равны)");
        }
        return secondMax;
    }

    public static void main(String[] args) {
        int[][] tests = {
                {10, 5, 10, 8}, // Обычный случай, ожидается 8
                {3, 3, 3}       // Должен бросить исключение
        };

        try {
            System.out.println("Второй максимум (8): " + findSecondMax(tests[0]));
            System.out.println(findSecondMax(tests[1]));
        } catch (Exception e) {
            System.out.println("Перехвачено ожидаемое исключение: " + e.getMessage());
        }
    }
}
