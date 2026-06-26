package homework_mock;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WordFrequency {
    /*
Подсчет частоты встречаемости слов в строке без учета регистра.
Важно: корректно разбить строку по пробелам и использовать getOrDefault().
*/

        public static Map<String, Integer> getWordFrequency(String str) {
            if (str == null || str.trim().isEmpty()) {
                return Collections.emptyMap();
            }

            Map<String, Integer> frequencyMap = new HashMap<>();
            // Разделение по одному или нескольким пробелам подряд
            String[] words = str.toLowerCase().split("\\s+");

            for (String word : words) {
                frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
            }
            return frequencyMap;
        }

        public static void main(String[] args) {
            String text = "Java   is fun and java is   powerful";
            String emptyText = "   ";

            System.out.println("Частота слов: " + getWordFrequency(text));
            System.out.println("Пустая строка: " + getWordFrequency(emptyText));
            System.out.println("Крайний случай (null): " + getWordFrequency(null));
        }
}
