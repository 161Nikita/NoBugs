package homework_mock.algos.task_algos;

/**
 * Является ли строка числом. Проверить, можно ли строку безопасно преобразовать в число.
 *
 * "1" -> true
 * "One" - false
 * "1 One" - false
 * "" - false
 * null - false
 */

public class IsNumber {

    public static boolean isNumber(String str) {

        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(isNumber("123"));
        System.out.println(isNumber("One"));
        System.out.println(isNumber("123One"));
        System.out.println(isNumber(""));
        System.out.println(isNumber(null));
    }
}
