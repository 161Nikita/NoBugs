package homework_mock.algos.task_algos;

/**
 * Вычислить факториал числа n (n!)
 * 4! -> 24
 * n < 0 -> IllegalArgumentException
 * <p>
 * Предполагаем, что число может быть не больше 12
 */

public class Factorial {

    public static int factorial(int num) {

        if (num < 0) {
            throw new IllegalArgumentException("Факториала отрицательного числа не существует");
        }
        int result = 1;

        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(factorial(0)); // 1
        System.out.println(factorial(1)); // 1
        System.out.println(factorial(12)); // 479001600

        try {
            System.out.println(factorial(-1));
        } catch (IllegalArgumentException e) {
            System.out.println("Поймали исключение! " + e.getMessage());
        }
    }
}
