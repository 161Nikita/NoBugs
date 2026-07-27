package homework_mock.algos.repeat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Фильтрация списка строк по префиксу. Оставить только строки начинающиеся на "А" (русская)
 * <p>
 * List<String> list1 = ("Апельсин", "Арбуз", "Дыня", "Клубника") -> ["Апельсин", "Арбуз"]
 * List<String> list2 = (" Апельсин", null, "Дыня", "Клубника") -> [" Апельсин"]
 * List<String> list3 = () -> []
 * List<String> list4 = ("Дыня", "Клубника") -> []
 * List<String> list5 = (null) -> IllegalArgumentException
 */

public class FilterPrefix {

    public static List<String> filterString(List<String> list) {

        if (list == null) {
            throw new IllegalArgumentException("В качестве аргумента null не должен передаваться");
        }

        return list.stream().filter(Objects::nonNull).filter(l -> l.trim().startsWith("А")).collect(Collectors.toList());
    }

    public static void main(String[] args) {

        List<String> list1 = new ArrayList<>(Arrays.asList("Апельсин", "Арбуз", "Дыня", "Клубника", "архимед")); // ["Апельсин", "Арбуз"]
        List<String> list2 = new ArrayList<>(Arrays.asList(" Апельсин ", null, "Дыня", "Клубника")); // [" Апельсин"]
        List<String> list3 = new ArrayList<>(Arrays.asList()); // []
        List<String> list4 = new ArrayList<>(Arrays.asList("Дыня", "Клубника")); // []

        System.out.println(filterString(list1));
        System.out.println(filterString(list2));
        System.out.println(filterString(list3));
        System.out.println(filterString(list4));

        try {
            filterString(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
    }
}
