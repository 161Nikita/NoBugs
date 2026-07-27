package homework_mock.algos.repeat;

/**
 * Проверка, является ли строка числом. Проверить, можно ли строку безопасно преобразовать в число
 * Обязательно использовать try-catch
 * <p>
 * "123" -> true
 * "123One" -> false
 * "One" -> false
 * "" -> false
 * "null" -> false
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

        System.out.println(isNumber("123")); // true
        System.out.println(isNumber("123One")); // false
        System.out.println(isNumber("One")); // false
        System.out.println(isNumber("")); // false
        System.out.println(isNumber(null)); // false
    }
}