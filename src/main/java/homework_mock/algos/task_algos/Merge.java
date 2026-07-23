package homework_mock.algos.task_algos;

import java.util.ArrayList;
import java.util.List;

/**
 * Слияние двух списков. Объединить два списка в один
 * <p>
 * List<String> a = "Privet", List<String> b = "World"; -> "PrivetWorld"
 * List<String> a = "Privet", List<String> b = " Mir"; -> "Privet Mir"
 * List<String> a = "", List<String> b = " Mir"; -> " Mir"
 * List<String> a = "", List<String> b = ""; -> ""
 * List<String> a = "Privet", List<String> b = null; -> IllegalArgumentException
 */

public class Merge {

    public static List<String> merge(List<String> a, List<String> b) {

        if (a == null || b == null) {
            throw new IllegalArgumentException("В качестве аргумента null быть не должно");
        }

        List<String> result = new ArrayList<>(a);
        result.addAll(b);
        return result;
    }

    public static void main(String[] args) {
        List<String> str1 = new ArrayList<>(List.of("Privet"));
        List<String> str2 = new ArrayList<>(List.of("World"));
        List<String> str3 = new ArrayList<>(List.of(" Mir"));
        List<String> str4 = new ArrayList<>(List.of(""));


        System.out.println(merge(str1, str2)); // [Privet, World]
        System.out.println(merge(str1, str3)); // [Privet,  Mir]
        System.out.println(merge(str4, str3)); // [,  Mir]
        System.out.println(merge(str4, str4)); // [, ]


        try {
            System.out.println(merge(str1, null)); // IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
    }
}