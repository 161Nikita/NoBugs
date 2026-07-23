package homework_mock.algos.task_algos;

/**
 * Определить, является ли число простым (делится только на 1 и на себя)
 * <p>
 * 5, 7, 11 -> true
 * 4, 6, 8 - false
 *
 */

public class IsPrime {

    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= num / i; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        System.out.println(isPrime(2)); // true
        System.out.println(isPrime(4)); // false
        System.out.println(isPrime(5)); // true
        System.out.println(isPrime(2147483647)); // true

    }
}
