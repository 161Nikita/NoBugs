package homework_mock.algos.repeat;

/**
 * Проверка палиндрома числа. Проверить, является ли число палиндромом
 * 121 -> true
 * 12321 -> true
 */

public class IsPalindromeNumber {

    public static boolean isPalindrome(int n) {

        if(n < 0) {
           return false;
        }

        int rev = 0;
        int original = n;

        while (n > 0) {

            rev = rev * 10 + n % 10;

            n /= 10;
        }
        return  original == rev;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(123));
    }
}
