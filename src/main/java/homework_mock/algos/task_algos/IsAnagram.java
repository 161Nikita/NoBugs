package homework_mock.algos.task_algos;

import java.util.Arrays;

public class IsAnagram {

    /**
     * Проверить, являются ли строки анаграммами
     * <p>
     * "Сон" - "Нос" -> true
     * "Окно и ветер" - "Откровение " -> true
     * null -> IllegalArgumentException
     * "" -> IllegalArgumentException
     */

    public static boolean isAnagram(String s1, String s2) {

        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("Строки не могут быть null");
        }
        if (s1.isEmpty() || s2.isEmpty()) {
            throw new IllegalArgumentException("Строки не могут быть пустыми");
        }

        String cleans1 = s1.replace(" ", "").toLowerCase();
        String cleans2 = s2.replace(" ", "").toLowerCase();

        if (cleans1.isEmpty() || cleans2.isEmpty()) {
            throw new IllegalArgumentException("Строки не могут быть пустыми");
        }


        if (cleans1.length() != cleans2.length()) {
            return false;
        }

        char[] cs1 = cleans1.toCharArray();
        char[] cs2 = cleans2.toCharArray();

        Arrays.sort(cs1);
        Arrays.sort(cs2);


        return Arrays.equals(cs1, cs2);
    }

    public static void main(String[] args) {

        String s1 = "Окно и ветер";
        String s2 = "Откровение ";
        String s3 = "ос";

        System.out.println(isAnagram(s1, s2));
        System.out.println(isAnagram(s1, s3));


        try {
            System.out.println(isAnagram(null, null));
        } catch (IllegalArgumentException e) {
            System.out.println("Отловили исключение! " + e.getMessage());
        }

        try {
            System.out.println(isAnagram("", "123"));
        } catch (IllegalArgumentException e) {
            System.out.println("Отловили исключение! " + e.getMessage());
        }
    }
}
