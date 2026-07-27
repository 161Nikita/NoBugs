package homework_mock.algos.task_algos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Фильтрация списка строк по префиксу. Оставить только строки начинающиеся на "А"
 * <p>
 * List<String> = new ArrayList{"Архипелаг", "Архиважный", "Прекрасный", "Никита"}; -> ["Архипелаг", "Архиважный"]
 * List<String> = new ArrayList{" Архипелаг", null, "Прекрасный", "Никита"}; -> [" Архипелаг"]
 * filterString(filterString(null)) -> IllegalArgumentException
 * () -> []
 */

public class FilterPrefix {

    public static List<String> filterString(List<String> list) {

        if (list == null) {
            throw new IllegalArgumentException("В качестве аргумента не должен передаваться null");
        }

        return list.stream().filter(Objects::nonNull).filter(l -> l.trim().startsWith("А")).collect(Collectors.toList());
    }

    public static void main(String[] args) {

        List<String> list1 = new ArrayList<>(Arrays.asList("Архипелаг", "Архиважный", "Прекрасный", "Никита"));
        List<String> list2 = new ArrayList<>(Arrays.asList(" Архипелаг", null, "Прекрасный", "Никита"));
        List<String> list3 = new ArrayList<>(Arrays.asList());


        System.out.println(filterString(list1));
        System.out.println(filterString(list2));
        System.out.println(filterString(list3));

        try {
            filterString(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
    }
}
