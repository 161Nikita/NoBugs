package homework_mock.algos.repeat;

/**
 * Вернуть факториал числа n рекурсивно
 *
 * 3! - 6
 * 4! - 24
 */

public class FactorialRecursivno {

    public static int factorial(int num) {

        if (num < 0) {
            return 0;
        }

        if (num <= 1) {
            return 1;
        }

        int i = num * factorial(num - 1);

        return i;
    }

    public static void main(String[] args) {
        System.out.println(factorial(3)); // 6
        System.out.println(factorial(4)); // 24
        System.out.println(factorial(0)); // 1
        System.out.println(factorial(-1)); // 0
    }
}