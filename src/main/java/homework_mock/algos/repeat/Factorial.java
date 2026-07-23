package homework_mock.algos.repeat;

public class Factorial {

    /**
     * Найти факториал числа (число не должно быть больше 12)
     * <p>
     * 4! -> 24
     * 0! -> 1
     * 1! -> 1
     * <0 -> IllegalArgumentException
     */

    public static int factorial(int num) {
        if (num < 0 || num > 12) {
            throw new IllegalArgumentException("Число должно быть в диапазоне от 0 до 12 включительно");
        }

        int count = 1;

        for (int i = 2; i <= num; i++) {
            count *= i;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(factorial(4)); // 24
        System.out.println(factorial(0)); // 1
        System.out.println(factorial(1)); // 1

        try {
            System.out.println(factorial(-1));
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
        try {
            System.out.println(factorial(13));
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
    }

}
