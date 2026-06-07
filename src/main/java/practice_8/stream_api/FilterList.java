package practice_8.stream_api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class FilterList {
    public static void main(String[] args) {

        // Создать список целых чисел, отфильтровать все четные числа и суммировать их
        // Промежуточная операция: фильтрация по четности
        // Терминальная операция: суммирование
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        int sum = numbers.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(Integer::valueOf)
                .sum();
        System.out.println(sum);

        /*IntStream stream = numbers.stream()
                .filter(n -> {
                    System.out.println("filter: " + n);
                    return n % 2 == 0;
                })
                .mapToInt(n -> {
                    System.out.println("mapTiInt " + n);
                        return n;
                });
        System.out.println("Стрим создан, но терминальный метод еще не вызван");

        int sum = stream.sum();

        System.out.println(sum);

    }*/

    }
}
