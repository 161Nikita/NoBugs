package homework_mock.algos.task_algos;

public class Palindrome {

    /**
     * Проверить, является ли строка палиндромом (одинаково читается
     * в обе стороны). Удалять знаки препинания, null, "Nikita"
     * <p>
     * String str = "Аргентина манит негра!";
     */

    public static boolean isPalindrome(String str) {

        if (str == null) {
            return false;
        }

       String cleaned = str.replaceAll("[^a-zA-Zа-яА-Я0-9]", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();

        return cleaned.equals(reversed);
    }


    public static void main(String[] args) {

        String str1 = "Аргентина манит негра!";
        String str2 = null;
        String str3 = "Nikita";

        System.out.println(isPalindrome(str1)); // true
        System.out.println(isPalindrome(str2)); // false
        System.out.println(isPalindrome(str3)); // false

    }
}
