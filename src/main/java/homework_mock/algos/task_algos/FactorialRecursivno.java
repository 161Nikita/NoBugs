package homework_mock.algos.task_algos;

/**
 * Факториал (рекурсивно). Вернуть факториал числа n.
 * <p>
 * 3! - 6
 * 5! - 120
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
        System.out.println(factorial(3));
        System.out.println(factorial(1));
    }
}
