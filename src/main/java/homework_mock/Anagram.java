package homework_mock;

import java.util.Arrays;

public class Anagram {
    public static boolean isAnagram(String a, String b) {
        if (a == null || b == null) return false;

        // Удаляем пробелы и приводим к нижнему регистру
        char[] arrayA = a.replaceAll("\\s+", "").toLowerCase().toCharArray();
        char[] arrayB = b.replaceAll("\\s+", "").toLowerCase().toCharArray();

        if (arrayA.length != arrayB.length) return false;

        Arrays.sort(arrayA);
        Arrays.sort(arrayB);

        return Arrays.equals(arrayA, arrayB);
    }

    public static void main(String[] args) {
        System.out.println("Listen и Silent (true): " + isAnagram("Listen", "Silent"));
        System.out.println("Hello и World (false): " + isAnagram("Hello", "World"));
        System.out.println("Обработка null (false): " + isAnagram(null, "Test"));
    }
}
