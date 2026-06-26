package homework_mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StreamFilters {

    public static List<String> filterByPrefix(List<String> list, String prefix) {
        if (list == null || prefix == null) return Collections.emptyList();
        return list.stream()
                .filter(s -> s != null && s.startsWith(prefix))
                .collect(Collectors.toList());
    }

    public static long countEvenNumbers(List<Integer> list) {
        if (list == null) return 0;
        return list.stream()
                .filter(n -> n != null && n % 2 == 0)
                .count();
    }

    public static void main(String[] args) {
        List<String> words = Arrays.asList("прекрасный", "привет", "предок", null);
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, null);

        System.out.println("Фильтр по 'пре': " + filterByPrefix(words, "пре"));
        System.out.println("Количество четных (3): " + countEvenNumbers(numbers));
    }
}
