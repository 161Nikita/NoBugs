package homework_mock.algos.repeat;

import java.util.Arrays;

/**
 * Проверка анаграмм. Проверить, являются ли строки анаграммами
 * "Ракета" - "Карета" - true
 * "Вход" - "Вдох" - true
 */

public class AnagramCheck {

    public static boolean isAnagram(String a, String b) {

        if (a == null || b == null) {
            return false;
        }

        char[] ca = a.replaceAll("\\s+", "").toLowerCase().toCharArray();
        char[] cb = b.replaceAll("\\s+", "").toLowerCase().toCharArray();

        if (ca.length != cb.length) {
            return false;
        }

        Arrays.sort(ca);
        Arrays.sort(cb);

        return Arrays.equals(ca, cb);
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("Ракета", "Карета")); // true
        System.out.println(isAnagram("Ракет", "Карета")); // false
        System.out.println(isAnagram("", "Карета")); // false
        System.out.println(isAnagram(null, "Карета")); // false
    }
}