package homework_mock.algos.repeat;

/**
 * Проверка четности числа. Вернуть true, если число четное
 * <p>
 * 2 -> true
 * -2 -> true
 * 0 -> true
 * -1 -> false
 * 1 -> false
 */

public class IsEven {

    public static boolean isEven(int num) {
        return num % 2 == 0;
    }

    public static void main(String[] args) {

        System.out.println(isEven(2)); // true
        System.out.println(isEven(-2)); // true
        System.out.println(isEven(0)); // true
        System.out.println(isEven(-1)); // false
        System.out.println(isEven(1)); // false
    }
}