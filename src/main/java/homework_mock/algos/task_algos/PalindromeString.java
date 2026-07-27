package homework_mock.algos.task_algos;

/**
 * Палиндром. Проверить, является ли строка палиндромом (без учета регистра и пробелов)
 * <p>
 * String str1 = "Аргентина манит негра" - true
 * String str2 = "Шалаш кока" - false
 */

public class PalindromeString {

    public static boolean isPalindrome(String str) {

        if (str == null || str.isEmpty()) {
            return false;
        }

        String cleaned = str.toLowerCase().replaceAll("\\s+", "");

        StringBuilder sb = new StringBuilder(cleaned);

        String reversed = sb.reverse().toString();

        return cleaned.equals(reversed);
    }

    public static void main(String[] args) {

        System.out.println(isPalindrome("Аргентина манит негра"));
        System.out.println(isPalindrome("Шалаш"));
        System.out.println(isPalindrome(""));
        System.out.println(isPalindrome(null));
    }
}