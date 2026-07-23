package homework_mock.algos.repeat;

/**
 * Разворот строки. Вернуть строку в обратном порядке.
 * <p>
 * "Привет" -> "тевирп"
 */

public class ReverseString {

    public static String reverseString(String str) {

        if (str == null) {
            return null;
        }

        String reverse = new StringBuilder(str).reverse().toString();

        return reverse;
    }

    public static void main(String[] args) {
        System.out.println(reverseString("Привет")); // тевирП
        System.out.println(reverseString("")); // ""
        System.out.println(reverseString(null)); // null
    }
}