package homework_mock.algos.task_algos;

/**
 * Обработка деления на 0. Написать метод деления с обработкой исключения деления на ноль
 * 10 / 2 -> 5
 * 10 / 3 -> 3
 * 0 / 10 -> 0
 * 10 / 0 -> ArithmeticException
 */

public class SafeDivide {

    public static int safeDivide(int a, int b) {

        try {
            return a / b;
        } catch (ArithmeticException e) {
            return 0;
        }

    }

    public static void main(String[] args) {

        System.out.println(safeDivide(10, 2)); // 5
        System.out.println(safeDivide(10, 3)); // 3
        System.out.println(safeDivide(0, 10)); // 0
        System.out.println(safeDivide(10, 0)); // 0



    }
}
