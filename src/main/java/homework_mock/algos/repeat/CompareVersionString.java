package homework_mock.algos.repeat;

/**
 * Сравнение версий строк. Сравнить строки вида 1.2.3 и 1.10.1 по версиям
 * v1 первая версия
 * v2 вторая версия
 * отрицательное число, если v1 < v2;
 * положительное число, если v1 > v2;
 * ноль, если версии равны.
 * IllegalArgumentException если одна из строк null или пустая
 * <p>
 * Есть строка "" нужно узнать количество слов
 */

public class CompareVersionString {

    public static int compareVersion(String v1, String v2) {

        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException("Передача null в качестве аргумента недопустима");
        }
        if (v1.trim().isEmpty() || v2.trim().isEmpty()) {
            throw new IllegalArgumentException("Строка версии не может быть пустой");
        }

        String[] a = v1.split("\\.");
        String[] b = v2.split("\\.");

        int maxLength = Math.max(a.length, b.length);

        for (int i = 0; i < maxLength; i++) {
            int n1 = i < a.length ? Integer.parseInt(a[i]) : 0;
            int n2 = i < b.length ? Integer.parseInt(b[i]) : 0;

            if (n1 != n2) {
                return n1 - n2;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("Тест 1 (должно быть < 0): " + compareVersion("1.2.3", "1.10.1"));


        System.out.println("Тест 2 (должно быть 0):   " + compareVersion("1.0.0", "1"));


        System.out.println("Тест 3 (должно быть > 0): " + compareVersion("2.1", "1.9.9"));

        try {
            compareVersion("1.0", null);
        } catch (IllegalArgumentException e) {
            System.out.println("Тест 4 (успешный перехват null): " + e.getMessage());
        }
    }
}
