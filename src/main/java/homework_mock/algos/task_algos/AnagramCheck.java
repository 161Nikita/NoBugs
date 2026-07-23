package homework_mock.algos.task_algos;

import java.util.Arrays;

/**
 * Проверка анаграмм. Проверить, являются ли строки анаграммами
 * <p>
 * "Ракета" - "Карета" - true
 * "Вход" - "Вдох" - true
 */

public class AnagramCheck {

    public static boolean isAnagram(String a, String b) {

        if (a == null || b == null) {
            return false;
        }

        char[] ca = a.toLowerCase().replaceAll("\\s+", "").toCharArray();
        char[] cb = b.toLowerCase().replaceAll("\\s+", "").toCharArray();

        if (ca.length != cb.length){
            return false;
        }

        Arrays.sort(ca);
        Arrays.sort(cb);

        return Arrays.equals(ca, cb);
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("Ракета", "Карета"));
        System.out.println(isAnagram("Вход", "Вдох"));
        System.out.println(isAnagram("Вход", ""));
        System.out.println(isAnagram(null, "Вдох"));
    }
}
