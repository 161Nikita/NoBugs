package homework_mock.algos.repeat;

import java.util.ArrayList;
import java.util.List;

/**
 * Слияние двух списков. Объединить два списка в один
 * <p>
 * "Privet" "world" -> [Privet, world]
 * "Privet", "big" " мир" -> [Privet, big,  мир]
 * "", "big" " мир" -> [, big,  мир]
 * null  "мир" -> IllegalArgumentException
 */

public class Merge {

    public static List<String> merge(List<String> a, List<String> b) {

        if (a == null || b == null) {
            throw new IllegalArgumentException("В качестве аргумента null быть не должен");
        }

        List<String> result = new ArrayList<>(a);
        result.addAll(b);

        return result;
    }

    public static void main(String[] args) {

        List<String> l1 = new ArrayList<>(List.of("Privet"));
        List<String> l2 = new ArrayList<>(List.of("world"));
        List<String> l3 = new ArrayList<>(List.of("Privet", "big"));
        List<String> l4 = new ArrayList<>(List.of(" мир"));
        List<String> l5 = new ArrayList<>(List.of("", "big"));

        System.out.println(merge(l1, l2)); // [Privet, world]
        System.out.println(merge(l3, l4)); // [Privet, big,  мир]
        System.out.println(merge(l5, l4)); // [, big,  мир]


        try {
            merge(null, l4);
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
    }
}