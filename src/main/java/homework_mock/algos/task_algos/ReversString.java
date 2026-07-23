package homework_mock.algos.task_algos;

/**
 * Разворот строки. Вернуть строку в обратном порядке.
 *
 * "Привет" -> "тевирП"
 */

public class ReversString {

    public static String reversString(String str) {

        if (str == null) {
            return null;
        }

        String reverse = new StringBuilder(str).reverse().toString();
        return reverse;
    }

    public static void main(String[] args) {
        System.out.println(reversString("Привет"));
        System.out.println(reversString(""));
        System.out.println(reversString(null));
    }
}