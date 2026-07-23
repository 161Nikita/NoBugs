package homework_mock.algos.task_algos;

/**
 * Реализация starwith без стандартных методов. Дана строка. Проверить, начинается ли строка с префикса
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
        System.out.println(isStartsWith("Привет", "При"));
        System.out.println(isStartsWith("Привет", "пока"));
        System.out.println(isStartsWith("При", "Привет"));
        System.out.println(isStartsWith("Привет", ""));
        System.out.println(isStartsWith(null, "При"));
    }
}