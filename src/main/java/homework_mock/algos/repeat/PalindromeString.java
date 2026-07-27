package homework_mock.algos.repeat;

/**
 * Палиндром. Проверить, является ли строка палиндромом (без учета регистра и пробелов)
 * <p>
 * Шалаш -> true
 * Аргентина манит негра -> true
 * привет -> false
 */

public class PalindromeString {

    public static boolean isPalindrome(String str) {

        if (str == null || str.isEmpty()) {
            return false;
        }

        String cleaned = str.toLowerCase().replaceAll("\\s+", "");

        StringBuilder sb = new StringBuilder(cleaned);

        String reversed = sb.reverse().toString();

        return reversed.equals(cleaned);

    }

    public static void main(String[] args) {

        System.out.println(isPalindrome("Шалаш")); // true
        System.out.println(isPalindrome(" Аргентина манит негра")); // true
        System.out.println(isPalindrome("привет")); // false
        System.out.println(isPalindrome("")); // false
        System.out.println(isPalindrome(null)); // false
    }
}