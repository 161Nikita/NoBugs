package practice_8_func_lambda.stream_api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortedUniqueElements {
    public static void main(String[] args) {
        // список с дублирующим значением
        // вывести все уникальные значения в отсортированном порядке

        List<Integer> numbers = Arrays.asList(3,2,3,1,4,2,5);

        /*List<Integer> uniqueSorted = numbers.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println(uniqueSorted);*/

        List<Integer> uniqueSorted = numbers.stream()
                .distinct()
                .peek(n -> System.out.println("distinct: " + n))
                .sorted()
                .peek(n -> System.out.println("sorted: " + n))
                .collect(Collectors.toList());

        System.out.println(uniqueSorted);

    }


}
