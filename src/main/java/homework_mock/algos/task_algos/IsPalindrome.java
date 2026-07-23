package homework_mock.algos.task_algos;

/**
 * Проверка палиндрома числа. Проверить, является ли число палиндромом
 * 121 -> true
 * 12321 -> true
 */

public class IsPalindrome {

    public static boolean isPalindrome(int num){

        if (num < 0) {
            return false;
        }

        int rev = 0;
        int original = num;

        while (num > 0) {
            rev = rev * 10 + num % 10; // 123 - 32
            num /= 10;
        }

        return original == rev;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(12321));
        System.out.println(isPalindrome(123));
    }
}
