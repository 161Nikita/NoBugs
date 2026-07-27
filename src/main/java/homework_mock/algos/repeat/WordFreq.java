package homework_mock.algos.repeat;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Поиск частоты слов в строке. Подсчитать, сколько раз каждое слово встречается в строке.
 * <p>
 * String str = "Привет, как дела? Привет как" -> 2, 2, 1
 * String str = "Привет - это я. Красно-черный цвет. Привет-медведь черный" -> 1, 1, 1, 1, 1, 1
 * Привет -> 1
 * "" -> 0 безопасный возврат пустой карты
 * null -> IllegalArgumentException
 */

public class WordFreq {

    public static Map<String, Integer> wordFreq(String str) {

        if (str == null) {
            throw new IllegalArgumentException("В качестве аргумента null недопустим");
        }

        if (str.trim().isEmpty()) {
            return Collections.emptyMap();
        }
        Map<String, Integer> map = new HashMap<>();

        String cleaned = str.toLowerCase().replaceAll("\\s+-\\s+", " ").trim();

        for (String word : cleaned.split("[^a-zA-Zа-яА-Я0-9'-]+")) {
            if (!word.isEmpty()) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
        }
        return map;
    }

    public static void main(String[] args) {

        String str1 = "Привет, как дела? Привет как";
        String str2 = "Привет - это я. Красно-черный цвет. Привет-медведь черный";
        String str3 = "";
        String str4 = "Привет";

        System.out.println(wordFreq(str1));
        System.out.println(wordFreq(str2)); // 1, 1, 1, 1, 1, 1, 1
        System.out.println(wordFreq(str3));
        System.out.println(wordFreq(str4));

        try {
            System.out.println(wordFreq(null));
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
    }
}