package homework_mock.algos.repeat;

/**
 * StartWith. Реализация StartWith без стандартных методов.
 * <p>
 * Привет - При -> true
 * Привет - Пра -> false
 * Привет - "" -> true
 * "" - "При" -> false
 * null - "При" -> false
 */

public class StartsWith {

    public static boolean isStartsWith(String str, String prefix) {

        if (str == null || prefix == null) {
            return false;
        }

        if (prefix.length() > str.length()) {
            return false;
        }

        for (int i = 0; i < prefix.length(); i++) {

            if (str.charAt(i) != prefix.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        System.out.println(isStartsWith("Привет", "При")); // true
        System.out.println(isStartsWith("Привет", "Пра")); // false
        System.out.println(isStartsWith("Привет", "")); // true
        System.out.println(isStartsWith("", "при")); // false
        System.out.println(isStartsWith(null, "при")); // false
    }
}
